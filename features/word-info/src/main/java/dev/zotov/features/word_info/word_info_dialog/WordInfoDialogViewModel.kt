package dev.zotov.features.word_info.word_info_dialog

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zotov.features.word_info.utils.toUiModel
import dev.zotov.shared.services.SoundPlayerService
import dev.zotov.words_data.WordsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
internal class WordInfoDialogViewModel @Inject constructor(
    private val wordsRepository: WordsRepository,
    private val soundPlayerService: SoundPlayerService,
) : ViewModel() {

    private var _state = MutableLiveData<WordInfoDialogState>(WordInfoDialogState.Loading)
    val state: LiveData<WordInfoDialogState> get() = _state

    fun initialize(word: String) {
        viewModelScope.launch {
            val wordDefinition = withContext(Dispatchers.IO) {
                wordsRepository.getWordDefinition(word)
            }

            if (wordDefinition == null) {
                _state.value = WordInfoDialogState.Error(
                    word,
                    "Мы не смогли найти это слово в словаре :( ",
                )
            } else {
                _state.value = WordInfoDialogState.Idle(
                    wordInfo = wordDefinition.toUiModel(),
                )
            }
        }
    }

    fun playWordPronunciation() {
        val currentState = state.value
        if (currentState is WordInfoDialogState.Idle) {
            currentState.wordInfo.phonetics?.audio?.let { audio ->
                Log.d("audio", audio)
                viewModelScope.launch {
                    soundPlayerService.playFromUrl(audio)
                }
            }
        }
    }
}
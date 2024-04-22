package dev.zotov.features.word_game.word_game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zotov.features.word_game.models.WordGameResult
import dev.zotov.words_data.WordsRepository
import dev.zotov.words_data.models.Word
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.min

@HiltViewModel
class WordGameViewModel @Inject constructor(
    private val wordsRepository: WordsRepository,
) : ViewModel() {

    companion object {
        const val TAG = "WordGameViewModel"
    }

    private val _state = MutableLiveData<WordGameState>(WordGameState.Loading)
    val state: LiveData<WordGameState> get() = _state

    private val _answers = mutableListOf<WordGameResult.Answer>()

    fun initialize() {
        viewModelScope.launch {
            try {
                val questions = wordsRepository.getFiveQuestions()
                _state.value = WordGameState.Idle(
                    wordQuestions = questions,
                    currentQuestionIndex = 0,
                    currentQuestionState = WordVariantState.Idle,
                )
            } catch (e: Throwable) {
                Log.e(TAG, "Failed to initialize viewmodel", e)
                _state.value = WordGameState.Error
            }
        }
    }

    fun skipQuestion() {
        val currentState = _state.value
        if (currentState is WordGameState.Idle) {
            if (currentState.currentQuestionState is WordVariantState.Idle) {
                _answers.add(
                    WordGameResult.Answer(
                        english = currentState.currentQuestion.targetWord.english,
                        russian = null,
                        correct = false,
                    )
                )
            }

            nextQuestion()
        }
    }

    fun selectVariant(word: Word) {
        val currentState = _state.value
        if (currentState is WordGameState.Idle && currentState.currentQuestionState is WordVariantState.Idle) {
            val isCorrect = word == currentState.currentQuestion.targetWord

            _state.value = currentState.copy(
                currentQuestionState = word.let {
                    if (isCorrect) {
                        WordVariantState.Correct(it)
                    } else {
                        WordVariantState.InCorrect(it)
                    }
                },
            )

            _answers.add(
                WordGameResult.Answer(
                    english = currentState.currentQuestion.targetWord.english,
                    russian = word.russian,
                    correct = isCorrect,
                )
            )
        }
    }

    fun nextQuestion() {
        val currentState = _state.value
        if (currentState is WordGameState.Idle) {
            _state.value = currentState.copy(
                currentQuestionIndex = min(
                    currentState.currentQuestionIndex + 1,
                    currentState.wordQuestions.size - 1,
                ),
                currentQuestionState = WordVariantState.Idle,
            )
        }
    }

    val gameResult: WordGameResult?
        get() {
            val currentState = state.value

            if (currentState is WordGameState.Idle) {
                return WordGameResult(
                    score = _answers.count { it.correct },
                    maxScore = currentState.wordQuestions.size,
                    answers = _answers.toList(),
                )
            }

            return null
        }
}
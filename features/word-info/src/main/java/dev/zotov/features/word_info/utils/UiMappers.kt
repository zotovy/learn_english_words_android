package dev.zotov.features.word_info.utils

import android.util.Log
import dev.zotov.features.word_info.models.WordDefinitionUiModel
import dev.zotov.features.word_info.models.WordMeaningUiModel
import dev.zotov.words_data.models.WordDefinition
import dev.zotov.words_data.models.WordMeaning

internal fun WordMeaning.toUiModel(word: String): WordMeaningUiModel {
    return WordMeaningUiModel(
        word = word,
        partOfSpeech = partOfSpeech,
        definitions = definitions,
    )
}

internal fun WordDefinition.toUiModel(): WordDefinitionUiModel {
    Log.d("WordDefinition", this.toString())
    return WordDefinitionUiModel(
        word = this.word,
        phonetics = if (this.phonetics?.notEmpty == true) this.phonetics else null,
        meanings = this.meanings.map { it.toUiModel(this.word) },
    )
}
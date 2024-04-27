package dev.zotov.features.word_info.models

import dev.zotov.words_data.models.WordMeaningDefinition

internal data class WordMeaningUiModel(
    val word: String,
    val partOfSpeech: String,
    val definitions: List<WordMeaningDefinition>,
)

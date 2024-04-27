package dev.zotov.features.word_info.models

import dev.zotov.words_data.models.WordPhonetic

internal data class WordDefinitionUiModel(
    val word: String,
    val phonetics: WordPhonetic?,
    val meanings: List<WordMeaningUiModel>,
)

package dev.zotov.words_data.models

data class WordDefinition(
    val word: String,
    val phonetics: WordPhonetic?,
    val meanings: List<WordMeaning>,
)


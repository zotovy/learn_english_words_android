package dev.zotov.words_data.models

data class WordQuestion(
    val targetWord: Word,
    val variants: List<Word>,
)

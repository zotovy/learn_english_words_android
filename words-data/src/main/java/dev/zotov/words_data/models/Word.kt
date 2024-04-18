package dev.zotov.words_data.models

data class Word(
    val id: Long,
    val russian: String,
    val english: String,
    val conjunction: String,
)


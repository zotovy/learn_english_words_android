package dev.zotov.words_data.models

data class WordMeaning(
    val partOfSpeech: String,
    val definitions: List<WordMeaningDefinition>,
    val synonyms: List<String>,
    val antonyms: List<String>,
)

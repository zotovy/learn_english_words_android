package dev.zotov.database.entities

import kotlinx.serialization.Serializable

@Serializable
data class WordMeaningDBO(
    val partOfSpeech: String,
    val synonyms: List<String>,
    val antonyms: List<String>,
    val definitions: List<WordMeaningDefinitionDBO>,
)

package dev.zotov.database.entities

import kotlinx.serialization.Serializable

@Serializable
data class WordMeaningDefinitionDBO(
    val definition: String,
    val example: String?
)

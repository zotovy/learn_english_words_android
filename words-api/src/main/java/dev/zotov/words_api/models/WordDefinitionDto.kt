package dev.zotov.words_api.models

import kotlinx.serialization.Serializable

@Serializable
data class WordDefinitionDto(
    val word: String,
    val phonetics: List<Phonetic>,
    val meanings: List<Meaning>,
) {
    @Serializable
    data class Phonetic(
        val audio: String? = null,
        val text: String? = null,
    )

    @Serializable
    data class Meaning(
        val partOfSpeech: String,
        val definitions: List<Definition>,
        val synonyms: List<String>,
        val antonyms: List<String>,
    ) {
        @Serializable
        data class Definition(
            val definition: String,
            val example: String? = null,
        )
    }
}

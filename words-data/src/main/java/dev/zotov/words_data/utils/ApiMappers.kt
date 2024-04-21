package dev.zotov.words_data.utils

import dev.zotov.words_api.models.WordDefinitionDto
import dev.zotov.words_data.models.WordDefinition
import dev.zotov.words_data.models.WordMeaning
import dev.zotov.words_data.models.WordMeaningDefinition
import dev.zotov.words_data.models.WordPhonetic

fun WordDefinitionDto.toWordDefinition(): WordDefinition {
    return WordDefinition(
        word = this.word,
        phonetics = this.phonetics.firstOrNull { it.audio != null && it.text != null }
            ?.let { phonetic ->
                WordPhonetic(
                    audio = phonetic.audio,
                    text = phonetic.text,
                )
            },
        meanings = this.meanings.map { meaning ->
            WordMeaning(
                partOfSpeech = meaning.partOfSpeech,
                definitions = meaning.definitions.map { definition ->
                    WordMeaningDefinition(
                        definition = definition.definition,
                        example = definition.example,
                    )
                },
                synonyms = meaning.synonyms,
                antonyms = meaning.antonyms,
            )
        },
    )
}


package dev.zotov.words_data.utils

import dev.zotov.words_api.models.WordDefinitionDto
import dev.zotov.words_data.models.WordDefinition
import dev.zotov.words_data.models.WordMeaning
import dev.zotov.words_data.models.WordMeaningDefinition
import dev.zotov.words_data.models.WordPhonetic

fun WordDefinitionDto.toWordDefinition(): WordDefinition {
    var phonetic = phonetics.firstOrNull { !it.audio.isNullOrEmpty() && !it.text.isNullOrEmpty() }

    if (phonetic == null) {
        phonetic = phonetics.firstOrNull { !it.text.isNullOrEmpty() }
    }

    return WordDefinition(
        word = word,
        phonetics = phonetic?.let {
            WordPhonetic(
                audio = it.audio,
                text = it.text,
            )
        },
        meanings = meanings.map { meaning ->
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


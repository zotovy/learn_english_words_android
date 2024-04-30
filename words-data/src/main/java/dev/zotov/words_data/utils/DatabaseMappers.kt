package dev.zotov.words_data.utils

import dev.zotov.database.entities.WordDBO
import dev.zotov.database.entities.WordDefinitionDBO
import dev.zotov.database.entities.WordMeaningDBO
import dev.zotov.database.entities.WordMeaningDefinitionDBO
import dev.zotov.database.entities.WordPhoneticDBO
import dev.zotov.words_data.models.Word
import dev.zotov.words_data.models.WordDefinition
import dev.zotov.words_data.models.WordMeaning
import dev.zotov.words_data.models.WordMeaningDefinition
import dev.zotov.words_data.models.WordPhonetic

internal fun WordDBO.toWord(): Word {
    return Word(
        id = uid,
        russian = russian,
        english = english.split(Regex("[;,]")).first(),
        conjunction = conjunction,
    )
}

internal fun WordDefinitionDBO.toWordDefinition(): WordDefinition {
    return WordDefinition(
        word = this.word,
        phonetics = this.phonetic?.toWordPhonetic(),
        meanings = this.meanings.map { it.toWordMeaning() }
    )
}

internal fun WordPhoneticDBO.toWordPhonetic(): WordPhonetic {
    return WordPhonetic(
        audio = this.audio.let { if (it.isNullOrEmpty()) null else this.audio },
        text = this.text.let { if (it.isNullOrEmpty()) null else this.text },
    )
}

internal fun WordMeaningDBO.toWordMeaning(): WordMeaning {
    return WordMeaning(
        partOfSpeech = this.partOfSpeech,
        antonyms = this.antonyms,
        synonyms = this.synonyms,
        definitions = this.definitions.map { it.toWordMeaningDefinition() }
    )
}

internal fun WordMeaningDefinitionDBO.toWordMeaningDefinition(): WordMeaningDefinition {
    return WordMeaningDefinition(
        definition = this.definition,
        example = this.example,
    )
}


internal fun WordDefinition.toWordDefinitionDBO(): WordDefinitionDBO {
    return WordDefinitionDBO(
        word = this.word,
        meanings = this.meanings.map { it.toWordMeaningDBO() },
        phonetic = this.phonetics?.toWordPhoneticDBO()
    )
}

internal fun WordMeaning.toWordMeaningDBO(): WordMeaningDBO {
    return WordMeaningDBO(
        partOfSpeech = this.partOfSpeech,
        synonyms = this.synonyms,
        antonyms = this.antonyms,
        definitions = this.definitions.map { it.toWordMeaningDefinitionDBO() }
    )
}

internal fun WordMeaningDefinition.toWordMeaningDefinitionDBO(): WordMeaningDefinitionDBO {
    return WordMeaningDefinitionDBO(
        definition = this.definition,
        example = this.example,
    )
}

internal fun WordPhonetic.toWordPhoneticDBO(): WordPhoneticDBO {
    return WordPhoneticDBO(
        audio = this.audio,
        text = this.text,
    )
}

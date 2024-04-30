package dev.zotov.words_data.utils

import dev.zotov.database.entities.WordDBO
import dev.zotov.database.entities.WordDefinitionDBO
import dev.zotov.database.entities.WordMeaningDBO
import dev.zotov.database.entities.WordMeaningDefinitionDBO
import dev.zotov.database.entities.WordPhoneticDBO
import dev.zotov.words_api.models.WordDefinitionDto
import dev.zotov.words_data.models.Word
import dev.zotov.words_data.models.WordDefinition
import dev.zotov.words_data.models.WordMeaning
import dev.zotov.words_data.models.WordMeaningDefinition
import dev.zotov.words_data.models.WordPhonetic

object TestData {
    object WordDBOs {
        val wordLight = WordDBO(
            uid = 1,
            russian = "Легкий",
            english = "Light",
            conjunction = "adjective"
        )

        val wordHeavy = WordDBO(
            uid = 2,
            russian = "Тяжелый",
            english = "Heavy",
            conjunction = "adjective"
        )
    }

    object Words {
        val wordLight = Word(
            id = 1,
            russian = "Легкий",
            english = "Light",
            conjunction = "adjective"
        )

        val wordHeavy = Word(
            id = 2,
            russian = "Тяжелый",
            english = "Heavy",
            conjunction = "adjective"
        )
    }

    object WordDefinitionDtos {
        val wordLight = WordDefinitionDto(
            word = "light",
            phonetics = listOf(
                WordDefinitionDto.Phonetic(text = "/lʌɪt/"),
            ),
            meanings = listOf(
                WordDefinitionDto.Meaning(
                    partOfSpeech = "noun",
                    definitions = listOf(
                        WordDefinitionDto.Meaning.Definition(
                            definition = "the natural agent that stimulates sight and makes things visible.",
                            example = "the light of the sun",
                        ),
                    ),
                    synonyms = listOf("illumination", "brightness", "radiance"),
                    antonyms = listOf("darkness", "obscurity"),
                ),
                WordDefinitionDto.Meaning(
                    partOfSpeech = "verb",
                    definitions = listOf(
                        WordDefinitionDto.Meaning.Definition(
                            definition = "provide with light or lighting; illuminate.",
                            example = "the room was lighted by a single bulb",
                        ),
                    ),
                    synonyms = listOf("illuminate", "brighten", "shine"),
                    antonyms = listOf("darken", "obscure"),
                ),
            ),
        )

        val wordDog = WordDefinitionDto(
            word = "dog",
            phonetics = listOf(
                WordDefinitionDto.Phonetic(text = "/dɔɡ/", audio = "http://test.com/dog.mp3"),
            ),
            meanings = listOf(
                WordDefinitionDto.Meaning(
                    partOfSpeech = "noun",
                    definitions = listOf(
                        WordDefinitionDto.Meaning.Definition(
                            definition = "a domesticated carnivorous mammal that typically has a long snout, an acute sense of smell, and a barking, howling, or whining voice.",
                            example = "a police dog",
                        ),
                    ),
                    synonyms = listOf("canine", "pooch", "hound"),
                    antonyms = listOf(),
                ),
                WordDefinitionDto.Meaning(
                    partOfSpeech = "verb",
                    definitions = listOf(
                        WordDefinitionDto.Meaning.Definition(
                            definition = "follow (someone or their movements) closely and persistently.",
                            example = "she dogged his footsteps",
                        ),
                    ),
                    synonyms = listOf("trail", "stalk", "pursue"),
                    antonyms = listOf("lead", "guide"),
                ),
            ),
        )

        val wordKotlin = WordDefinitionDto(
            word = "kotlin",
            phonetics = listOf(
                WordDefinitionDto.Phonetic(text = "/ˈkɒtlɪn/"),
                WordDefinitionDto.Phonetic(
                    text = "/ˈkoootlɪn/",
                    audio = "https://test.com/kot.mp3"
                ),
                WordDefinitionDto.Phonetic(),
            ),
            meanings = listOf(
                WordDefinitionDto.Meaning(
                    partOfSpeech = "noun",
                    definitions = listOf(
                        WordDefinitionDto.Meaning.Definition(
                            definition = "a statically typed programming language for modern multiplatform applications.",
                            example = "Kotlin was officially released as a new language for Android Development by Google",
                        ),
                    ),
                    synonyms = listOf("programming language", "software development language"),
                    antonyms = listOf(),
                ),
            ),
        )

        val wordRun = WordDefinitionDto(
            word = "run",
            phonetics = emptyList(),
            meanings = listOf(
                WordDefinitionDto.Meaning(
                    partOfSpeech = "verb",
                    definitions = listOf(
                        WordDefinitionDto.Meaning.Definition(
                            definition = "move at a speed faster than a walk, never having both or all the feet on the ground at the same time.",
                            example = "cheetahs can run up to 60 mph",
                        ),
                        WordDefinitionDto.Meaning.Definition(
                            definition = "travel a specified distance, especially on foot or in a vehicle.",
                            example = "she ran 5 miles",
                        ),
                    ),
                    synonyms = listOf("sprint", "jog", "dash"),
                    antonyms = listOf("walk", "stroll"),
                ),
                WordDefinitionDto.Meaning(
                    partOfSpeech = "noun",
                    definitions = listOf(
                        WordDefinitionDto.Meaning.Definition(
                            definition = "an act or spell of running.",
                            example = "she's had a run of bad luck",
                        ),
                    ),
                    synonyms = listOf("dash", "sprint", "jog"),
                    antonyms = listOf("walk", "stroll"),
                ),
            ),
        )
    }

    object WordDefinitions {
        val wordLight = WordDefinition(
            word = "light",
            phonetics = WordPhonetic(
                audio = null,
                text = "/lʌɪt/",
            ),
            meanings = listOf(
                WordMeaning(
                    partOfSpeech = "noun",
                    definitions = listOf(
                        WordMeaningDefinition(
                            definition = "the natural agent that stimulates sight and makes things visible.",
                            example = "the light of the sun",
                        ),
                    ),
                    synonyms = listOf("illumination", "brightness", "radiance"),
                    antonyms = listOf("darkness", "obscurity"),
                ),
                WordMeaning(
                    partOfSpeech = "verb",
                    definitions = listOf(
                        WordMeaningDefinition(
                            definition = "provide with light or lighting; illuminate.",
                            example = "the room was lighted by a single bulb",
                        ),
                    ),
                    synonyms = listOf("illuminate", "brighten", "shine"),
                    antonyms = listOf("darken", "obscure"),
                ),
            ),
        )

        val wordDog = WordDefinition(
            word = "dog",
            phonetics = WordPhonetic(text = "/dɔɡ/", audio = "http://test.com/dog.mp3"),
            meanings = listOf(
                WordMeaning(
                    partOfSpeech = "noun",
                    definitions = listOf(
                        WordMeaningDefinition(
                            definition = "a domesticated carnivorous mammal that typically has a long snout, an acute sense of smell, and a barking, howling, or whining voice.",
                            example = "a police dog",
                        ),
                    ),
                    synonyms = listOf("canine", "pooch", "hound"),
                    antonyms = listOf(),
                ),
                WordMeaning(
                    partOfSpeech = "verb",
                    definitions = listOf(
                        WordMeaningDefinition(
                            definition = "follow (someone or their movements) closely and persistently.",
                            example = "she dogged his footsteps",
                        ),
                    ),
                    synonyms = listOf("trail", "stalk", "pursue"),
                    antonyms = listOf("lead", "guide"),
                ),
            ),
        )

        val wordKotlin = WordDefinition(
            word = "kotlin",
            phonetics = WordPhonetic(
                text = "/ˈkoootlɪn/",
                audio = "https://test.com/kot.mp3"
            ),
            meanings = listOf(
                WordMeaning(
                    partOfSpeech = "noun",
                    definitions = listOf(
                       WordMeaningDefinition(
                            definition = "a statically typed programming language for modern multiplatform applications.",
                            example = "Kotlin was officially released as a new language for Android Development by Google",
                        ),
                    ),
                    synonyms = listOf("programming language", "software development language"),
                    antonyms = listOf(),
                ),
            ),
        )

        val wordRun = WordDefinition(
            word = "run",
            phonetics = null,
            meanings = listOf(
                WordMeaning(
                    partOfSpeech = "verb",
                    definitions = listOf(
                        WordMeaningDefinition(
                            definition = "move at a speed faster than a walk, never having both or all the feet on the ground at the same time.",
                            example = "cheetahs can run up to 60 mph",
                        ),
                        WordMeaningDefinition(
                            definition = "travel a specified distance, especially on foot or in a vehicle.",
                            example = "she ran 5 miles",
                        ),
                    ),
                    synonyms = listOf("sprint", "jog", "dash"),
                    antonyms = listOf("walk", "stroll"),
                ),
                WordMeaning(
                    partOfSpeech = "noun",
                    definitions = listOf(
                        WordMeaningDefinition(
                            definition = "an act or spell of running.",
                            example = "she's had a run of bad luck",
                        ),
                    ),
                    synonyms = listOf("dash", "sprint", "jog"),
                    antonyms = listOf("walk", "stroll"),
                ),
            ),
        )
    }

    object WordDefinitionDBOS {
        val wordLight = WordDefinitionDBO(
            word = "light",
            phonetic = WordPhoneticDBO(
                text = "/lʌɪt/",
                audio = null,
            ),
            meanings = listOf(
                WordMeaningDBO(
                    partOfSpeech = "noun",
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            definition = "the natural agent that stimulates sight and makes things visible.",
                            example = "the light of the sun",
                        ),
                    ),
                    synonyms = listOf("illumination", "brightness", "radiance"),
                    antonyms = listOf("darkness", "obscurity"),
                ),
                WordMeaningDBO(
                    partOfSpeech = "verb",
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            definition = "provide with light or lighting; illuminate.",
                            example = "the room was lighted by a single bulb",
                        ),
                    ),
                    synonyms = listOf("illuminate", "brighten", "shine"),
                    antonyms = listOf("darken", "obscure"),
                ),
            ),
        )

        val wordDog = WordDefinitionDBO(
            word = "dog",
            phonetic = WordPhoneticDBO(text = "/dɔɡ/", audio = "http://test.com/dog.mp3"),
            meanings = listOf(
                WordMeaningDBO(
                    partOfSpeech = "noun",
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            definition = "a domesticated carnivorous mammal that typically has a long snout, an acute sense of smell, and a barking, howling, or whining voice.",
                            example = "a police dog",
                        ),
                    ),
                    synonyms = listOf("canine", "pooch", "hound"),
                    antonyms = listOf(),
                ),
                WordMeaningDBO(
                    partOfSpeech = "verb",
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            definition = "follow (someone or their movements) closely and persistently.",
                            example = "she dogged his footsteps",
                        ),
                    ),
                    synonyms = listOf("trail", "stalk", "pursue"),
                    antonyms = listOf("lead", "guide"),
                ),
            ),
        )

        val wordRun = WordDefinitionDBO(
            word = "run",
            phonetic = null,
            meanings = listOf(
                WordMeaningDBO(
                    partOfSpeech = "verb",
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            definition = "move at a speed faster than a walk, never having both or all the feet on the ground at the same time.",
                            example = "cheetahs can run up to 60 mph",
                        ),
                        WordMeaningDefinitionDBO(
                            definition = "travel a specified distance, especially on foot or in a vehicle.",
                            example = "she ran 5 miles",
                        ),
                    ),
                    synonyms = listOf("sprint", "jog", "dash"),
                    antonyms = listOf("walk", "stroll"),
                ),
                WordMeaningDBO(
                    partOfSpeech = "noun",
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            definition = "an act or spell of running.",
                            example = "she's had a run of bad luck",
                        ),
                    ),
                    synonyms = listOf("dash", "sprint", "jog"),
                    antonyms = listOf("walk", "stroll"),
                ),
            ),
        )
    }
}
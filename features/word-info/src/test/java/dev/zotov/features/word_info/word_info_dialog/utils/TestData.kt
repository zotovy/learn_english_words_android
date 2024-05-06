package dev.zotov.features.word_info.word_info_dialog.utils

import dev.zotov.features.word_info.models.WordDefinitionUiModel
import dev.zotov.features.word_info.models.WordMeaningUiModel
import dev.zotov.words_data.models.WordDefinition
import dev.zotov.words_data.models.WordMeaning
import dev.zotov.words_data.models.WordMeaningDefinition
import dev.zotov.words_data.models.WordPhonetic

internal object TestData {
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

    object WordDefinitionUiModels {
        val wordLight = WordDefinitionUiModel(
            word = "light",
            phonetics = WordPhonetic(
                audio = null,
                text = "/lʌɪt/",
            ),
            meanings = listOf(
                WordMeaningUiModel(
                    word = "light",
                    partOfSpeech = "noun",
                    definitions = listOf(
                        WordMeaningDefinition(
                            definition = "the natural agent that stimulates sight and makes things visible.",
                            example = "the light of the sun",
                        ),
                    ),
                ),
                WordMeaningUiModel(
                    word = "light",
                    partOfSpeech = "verb",
                    definitions = listOf(
                        WordMeaningDefinition(
                            definition = "provide with light or lighting; illuminate.",
                            example = "the room was lighted by a single bulb",
                        ),
                    ),
                ),
            ),
        )
    }
}
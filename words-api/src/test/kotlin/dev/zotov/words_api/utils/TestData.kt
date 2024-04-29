package dev.zotov.words_api.utils

import dev.zotov.words_api.models.WordDefinitionDto

object TestData {

    object WordDefinitionDtos {
        fun all() = listOf(wordTest, wordExample, wordKotlin, wordDog, wordRun, wordLight)

        val wordTest = WordDefinitionDto(
            word = "test",
            phonetics = listOf(
                WordDefinitionDto.Phonetic(text = "/tɛst/"),
                WordDefinitionDto.Phonetic(text = "/tɛst/"),
            ),
            meanings = listOf(
                WordDefinitionDto.Meaning(
                    partOfSpeech = "noun",
                    definitions = listOf(
                        WordDefinitionDto.Meaning.Definition(
                            definition = "a procedure intended to establish the quality, performance, or reliability of something, especially before it is taken into widespread use.",
                            example = "simulation is used to test the feasibility of the design",
                        ),
                        WordDefinitionDto.Meaning.Definition(
                            definition = "a movable, small object used for indicating positions or scores in board games, etc.",
                        ),
                    ),
                    synonyms = listOf("trial", "experiment", "assessment"),
                    antonyms = listOf("confirmation", "certainty"),
                ),
                WordDefinitionDto.Meaning(
                    partOfSpeech = "verb",
                    definitions = listOf(
                        WordDefinitionDto.Meaning.Definition(
                            definition = "take measures to check the quality, performance, or reliability of (something), especially before putting it into widespread use or practice.",
                            example = "equipment should be tested for safety",
                        ),
                    ),
                    synonyms = listOf("try out", "experiment with", "assess"),
                    antonyms = listOf("confirm", "certify"),
                ),
            ),
        )

        val wordExample = WordDefinitionDto(
            word = "example",
            phonetics = listOf(
                WordDefinitionDto.Phonetic(text = "/ɪɡˈzamp(ə)l/"),
            ),
            meanings = listOf(
                WordDefinitionDto.Meaning(
                    partOfSpeech = "noun",
                    definitions = listOf(
                        WordDefinitionDto.Meaning.Definition(
                            definition = "a thing characteristic of its kind or illustrating a general rule.",
                            example = "the example of a tropical climate and its related fauna",
                        ),
                    ),
                    synonyms = listOf("illustration", "instance", "sample"),
                    antonyms = listOf("exception", "counterexample"),
                ),
                WordDefinitionDto.Meaning(
                    partOfSpeech = "verb",
                    definitions = listOf(
                        WordDefinitionDto.Meaning.Definition(
                            definition = "be illustrated or exemplified.",
                            example = "the rules are illustrated by actual examples",
                        ),
                    ),
                    synonyms = listOf("demonstrate", "show", "exemplify"),
                    antonyms = listOf("contradict", "refute"),
                ),
            ),
        )

        val wordKotlin = WordDefinitionDto(
            word = "kotlin",
            phonetics = listOf(
                WordDefinitionDto.Phonetic(text = "/ˈkɒtlɪn/"),
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
            phonetics = listOf(
                WordDefinitionDto.Phonetic(text = "/rʌn/"),
            ),
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

        val wordDog = WordDefinitionDto(
            word = "dog",
            phonetics = listOf(
                WordDefinitionDto.Phonetic(text = "/dɔɡ/"),
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

        val wordAccess = WordDefinitionDto(
            word = "access",
            phonetics = listOf(
                WordDefinitionDto.Phonetic(
                    text = "/ˈæksɛs/",
                    audio = "",
                ),
                WordDefinitionDto.Phonetic(
                    text = "/ˈækˌsɛs/",
                    audio = "https://api.dictionaryapi.dev/media/pronunciations/en/access-1-us.mp3",
                )
            ),
            meanings = listOf(
                WordDefinitionDto.Meaning(
                    partOfSpeech = "noun",
                    definitions = listOf(
                        WordDefinitionDto.Meaning.Definition(
                            definition = "A way or means of approaching or entering; an entrance; a passage.",
                        ),
                        WordDefinitionDto.Meaning.Definition(
                            definition = "The act of approaching or entering; an advance.",
                        ),
                        WordDefinitionDto.Meaning.Definition(
                            definition = "The right or ability of approaching or entering; admittance; admission; accessibility.",
                        ),
                        WordDefinitionDto.Meaning.Definition(
                            definition = "The quality of being easy to approach or enter.",
                        ),
                        WordDefinitionDto.Meaning.Definition(
                            definition = "Admission to sexual intercourse.",
                        ),
                        WordDefinitionDto.Meaning.Definition(
                            definition = "An increase by addition; accession",
                            example = "an access of territory"
                        ),
                        WordDefinitionDto.Meaning.Definition(
                            definition = "An onset, attack, or fit of disease; an ague fit.",
                        ),
                        WordDefinitionDto.Meaning.Definition(
                            definition = "An outburst of an emotion; a paroxysm; a fit of passion",
                        ),
                        WordDefinitionDto.Meaning.Definition(
                            definition = "The right of a noncustodial parent to visit their child.",
                        ),
                        WordDefinitionDto.Meaning.Definition(
                            definition = "The process of locating data in memory.",
                        ),
                        WordDefinitionDto.Meaning.Definition(
                            definition = "Connection to or communication with a computer program or to the Internet.",
                        )
                    ),
                    synonyms = emptyList(),
                    antonyms = emptyList()
                )
            ),
        )


        object Raw {
            val wordAccess =
                "[{\"word\":\"access\",\"phonetic\":\"/ˈæksɛs/\",\"phonetics\":[{\"text\":\"/ˈæksɛs/\",\"audio\":\"\"},{\"text\":\"/ˈækˌsɛs/\",\"audio\":\"https://api.dictionaryapi.dev/media/pronunciations/en/access-1-us.mp3\",\"sourceUrl\":\"https://commons.wikimedia.org/w/index.php?curid=1243832\",\"license\":{\"name\":\"BY-SA 3.0\",\"url\":\"https://creativecommons.org/licenses/by-sa/3.0\"}}],\"meanings\":[{\"partOfSpeech\":\"noun\",\"definitions\":[{\"definition\":\"A way or means of approaching or entering; an entrance; a passage.\",\"synonyms\":[],\"antonyms\":[]},{\"definition\":\"The act of approaching or entering; an advance.\",\"synonyms\":[],\"antonyms\":[]},{\"definition\":\"The right or ability of approaching or entering; admittance; admission; accessibility.\",\"synonyms\":[],\"antonyms\":[]},{\"definition\":\"The quality of being easy to approach or enter.\",\"synonyms\":[],\"antonyms\":[]},{\"definition\":\"Admission to sexual intercourse.\",\"synonyms\":[],\"antonyms\":[]},{\"definition\":\"An increase by addition; accession\",\"synonyms\":[],\"antonyms\":[],\"example\":\"an access of territory\"},{\"definition\":\"An onset, attack, or fit of disease; an ague fit.\",\"synonyms\":[],\"antonyms\":[]},{\"definition\":\"An outburst of an emotion; a paroxysm; a fit of passion\",\"synonyms\":[],\"antonyms\":[]},{\"definition\":\"The right of a noncustodial parent to visit their child.\",\"synonyms\":[],\"antonyms\":[]},{\"definition\":\"The process of locating data in memory.\",\"synonyms\":[],\"antonyms\":[]},{\"definition\":\"Connection to or communication with a computer program or to the Internet.\",\"synonyms\":[],\"antonyms\":[]}],\"synonyms\":[],\"antonyms\":[]}],\"license\":{\"name\":\"CC BY-SA 3.0\",\"url\":\"https://creativecommons.org/licenses/by-sa/3.0\"},\"sourceUrls\":[\"https://en.wiktionary.org/wiki/access\"]}]"
        }
    }
}
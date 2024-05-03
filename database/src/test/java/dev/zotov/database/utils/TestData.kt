package dev.zotov.database.utils

import dev.zotov.database.entities.WordDBO
import dev.zotov.database.entities.WordDefinitionDBO
import dev.zotov.database.entities.WordMeaningDBO
import dev.zotov.database.entities.WordMeaningDefinitionDBO
import dev.zotov.database.entities.WordPhoneticDBO

object TestData {
    object WordDBO {
        fun all() = listOf(
            wordLight,
            wordHeavy,
            wordFast,
            wordSlow,
            wordBig,
            wordCat,
            wordDog,
            wordBook,
            wordRun,
            wordJump,
            wordSwim,
            wordEat
        )

        // Adjectives
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

        val wordFast = WordDBO(
            uid = 3,
            russian = "Быстрый",
            english = "Fast",
            conjunction = "adjective"
        )

        val wordSlow = WordDBO(
            uid = 4,
            russian = "Медленный",
            english = "Slow",
            conjunction = "adjective"
        )

        val wordBig = WordDBO(
            uid = 5,
            russian = "Большой",
            english = "Big",
            conjunction = "adjective"
        )

        // Nouns
        val wordCat = WordDBO(
            uid = 6,
            russian = "Кот",
            english = "Cat",
            conjunction = "noun"
        )

        val wordDog = WordDBO(
            uid = 7,
            russian = "Собака",
            english = "Dog",
            conjunction = "noun"
        )

        val wordBook = WordDBO(
            uid = 8,
            russian = "Книга",
            english = "Book",
            conjunction = "noun"
        )

        // Verbs
        val wordRun = WordDBO(
            uid = 9,
            russian = "Бегать",
            english = "Run",
            conjunction = "verb"
        )

        val wordJump = WordDBO(
            uid = 10,
            russian = "Прыгать",
            english = "Jump",
            conjunction = "verb"
        )

        val wordSwim = WordDBO(
            uid = 11,
            russian = "Плавать",
            english = "Swim",
            conjunction = "verb"
        )

        val wordEat = WordDBO(
            uid = 12,
            russian = "Есть",
            english = "Eat",
            conjunction = "verb"
        )
    }

    object WordDefinitionDBO {

        fun all() = listOf(
            wordLight,
            wordHeavy,
            wordFast,
            wordSlow,
            wordBig,
            wordCat,
            wordDog,
            wordBook,
            wordRun,
            wordJump,
            wordSwim,
            wordEat
        )

        val wordLight = WordDefinitionDBO(
            word = "light",
            phonetic = WordPhoneticDBO(
                audio = null,
                text = "laɪt"
            ),
            meanings = listOf(
                WordMeaningDBO(
                    partOfSpeech = "Adjective",
                    synonyms = listOf("bright", "luminous", "radiant"),
                    antonyms = listOf("dark", "dim"),
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            "Having a considerable or sufficient amount of natural light.",
                            "The room was light and airy."
                        ),
                        WordMeaningDefinitionDBO(
                            "Not dark in color; pale.",
                            "She wore a light blue dress to the party."
                        )
                    )
                ),
                WordMeaningDBO(
                    partOfSpeech = "Noun",
                    synonyms = listOf("illumination", "brightness", "daylight"),
                    antonyms = listOf("darkness", "obscurity"),
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            "The natural agent that stimulates sight and makes things visible.",
                            "The morning light filtered through the curtains."
                        ),
                        WordMeaningDefinitionDBO(
                            "A source of illumination, especially an electric lamp.",
                            "Turn on the light, please."
                        )
                    )
                ),
            )
        )

        val wordHeavy = WordDefinitionDBO(
            word = "heavy",
            phonetic = WordPhoneticDBO(
                audio = null,
                text = "ˈhɛv·i"
            ),
            meanings = listOf(
                WordMeaningDBO(
                    partOfSpeech = "Adjective",
                    synonyms = listOf("weighty", "burdensome", "substantial"),
                    antonyms = listOf("light", "lightweight"),
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            "Of great weight; difficult to lift or move.",
                            "The box was too heavy for one person to carry."
                        ),
                        WordMeaningDefinitionDBO(
                            "Characterized by a high proportion of fuel to air.",
                            "The heavy fog made driving hazardous."
                        )
                    )
                ),
                WordMeaningDBO(
                    partOfSpeech = "Noun",
                    synonyms = listOf("burden", "load", "weight"),
                    antonyms = listOf("lightness", "unimportance"),
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            "An object having great weight.",
                            "She struggled under the heavy of her backpack."
                        ),
                        WordMeaningDefinitionDBO(
                            "A large, strong horse, especially one used for breeding or farm work.",
                            "The farmer hitched the heavy to the plow."
                        )
                    )
                ),
            )
        )

        val wordFast = WordDefinitionDBO(
            word = "fast",
            phonetic = WordPhoneticDBO(
                audio = null,
                text = "fæst"
            ),
            meanings = listOf(
                WordMeaningDBO(
                    partOfSpeech = "Adverb",
                    synonyms = listOf("quickly", "swiftly", "speedily"),
                    antonyms = listOf("slowly", "leisurely"),
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            "At high speed; quickly.",
                            "He ran fast to catch the bus."
                        ),
                        WordMeaningDefinitionDBO(
                            "In a firm or secure manner.",
                            "Hold on tight; the boat is moving fast."
                        )
                    )
                ),
                WordMeaningDBO(
                    partOfSpeech = "Adjective",
                    synonyms = listOf("rapid", "speedy", "quick"),
                    antonyms = listOf("slow", "leisurely"),
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            "Moving or capable of moving at high speed.",
                            "She's a fast runner."
                        ),
                        WordMeaningDefinitionDBO(
                            "Taking a short time to happen.",
                            "It was a fast decision."
                        )
                    )
                ),
            )
        )

        val wordSlow = WordDefinitionDBO(
            word = "slow",
            phonetic = WordPhoneticDBO(
                audio = null,
                text = "sloʊ"
            ),
            meanings = listOf(
                WordMeaningDBO(
                    partOfSpeech = "Adverb",
                    synonyms = listOf("slowly", "at a slow pace", "sluggishly"),
                    antonyms = listOf("quickly", "swiftly"),
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            "At a slow pace; not quickly.",
                            "He walked slow to enjoy the scenery."
                        ),
                        WordMeaningDefinitionDBO(
                            "Without hurry or activity.",
                            "Life moves slow in the countryside."
                        )
                    )
                ),
                WordMeaningDBO(
                    partOfSpeech = "Adjective",
                    synonyms = listOf("leisurely", "unhurried", "languid"),
                    antonyms = listOf("fast", "rapid"),
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            "Moving or operating at a slow speed.",
                            "The traffic was slow this morning."
                        ),
                        WordMeaningDefinitionDBO(
                            "Taking a long time to happen or complete.",
                            "Progress has been slow on the construction project."
                        )
                    )
                ),
            )
        )

        val wordBig = WordDefinitionDBO(
            word = "big",
            phonetic = WordPhoneticDBO(
                audio = null,
                text = "bɪɡ"
            ),
            meanings = listOf(
                WordMeaningDBO(
                    partOfSpeech = "Adjective",
                    synonyms = listOf("large", "huge", "gigantic"),
                    antonyms = listOf("small", "tiny"),
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            "Of considerable size or extent.",
                            "She bought a big house in the suburbs."
                        ),
                        WordMeaningDefinitionDBO(
                            "Important, serious, or significant.",
                            "It's a big decision to make."
                        )
                    )
                ),
                WordMeaningDBO(
                    partOfSpeech = "Noun",
                    synonyms = listOf("prominent", "important", "influential"),
                    antonyms = listOf("insignificant", "unimportant"),
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            "Of considerable importance or seriousness.",
                            "The team faced a big challenge."
                        ),
                        WordMeaningDefinitionDBO(
                            "Generous and kind-hearted.",
                            "She has a big heart and helps everyone in need."
                        )
                    )
                ),
                // Additional meanings can be added here
            )
        )

        val wordCat = WordDefinitionDBO(
            word = "cat",
            phonetic = WordPhoneticDBO(
                audio = null,
                text = "kæt"
            ),
            meanings = listOf(
                WordMeaningDBO(
                    partOfSpeech = "Noun",
                    synonyms = listOf("feline", "kitty", "puss"),
                    antonyms = listOf("dog", "canine"),
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            "A small domesticated carnivorous mammal with soft fur, a short snout, and retractile claws.",
                            "The cat rubbed against my leg, demanding attention."
                        ),
                        WordMeaningDefinitionDBO(
                            "A spiteful or unpleasant woman.",
                            "She's a real cat, always gossiping about others."
                        )
                    )
                ),
                // Additional meanings can be added here
            )
        )

        val wordDog = WordDefinitionDBO(
            word = "dog",
            phonetic = WordPhoneticDBO(
                audio = null,
                text = "dɔɡ"
            ),
            meanings = listOf(
                WordMeaningDBO(
                    partOfSpeech = "Noun",
                    synonyms = listOf("canine", "pooch", "hound"),
                    antonyms = listOf("cat", "feline"),
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            "A domesticated carnivorous mammal that typically has a long snout, an acute sense of smell, non-retractile claws, and a barking, howling, or whining voice.",
                            "The dog wagged its tail in excitement."
                        ),
                        WordMeaningDefinitionDBO(
                            "A despicable or contemptible man.",
                            "He's a real dog; he cheated on his wife."
                        )
                    )
                ),
            )
        )

        val wordBook = WordDefinitionDBO(
            word = "book",
            phonetic = WordPhoneticDBO(
                audio = null,
                text = "bʊk"
            ),
            meanings = listOf(
                WordMeaningDBO(
                    partOfSpeech = "Noun",
                    synonyms = listOf("volume", "tome", "publication"),
                    antonyms = listOf("e-book", "audiobook"),
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            "A written or printed work consisting of pages glued or sewn together along one side and bound in covers.",
                            "She picked up a book and started reading."
                        ),
                        WordMeaningDefinitionDBO(
                            "A bound set of blank sheets for writing or keeping records in.",
                            "He kept a record of his expenses in a small book."
                        )
                    )
                ),
            )
        )

        val wordRun = WordDefinitionDBO(
            word = "run",
            phonetic = WordPhoneticDBO(
                audio = null,
                text = "rʌn"
            ),
            meanings = listOf(
                WordMeaningDBO(
                    partOfSpeech = "Verb",
                    synonyms = listOf("sprint", "dash", "jog"),
                    antonyms = listOf("walk", "crawl"),
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            "Move at a speed faster than a walk, never having both or all the feet on the ground at the same time.",
                            "She runs every morning to stay fit."
                        ),
                        WordMeaningDefinitionDBO(
                            "Operate or be in charge of a business, organization, or activity.",
                            "He runs a successful restaurant in the city center."
                        )
                    )
                ),
                WordMeaningDBO(
                    partOfSpeech = "Noun",
                    synonyms = listOf("sprint", "race", "dash"),
                    antonyms = listOf("walk", "stroll"),
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            "An act of running a race.",
                            "He won the 100-meter run at the track meet."
                        ),
                        WordMeaningDefinitionDBO(
                            "A continuous period of being in operation.",
                            "The factory has been in run for over fifty years."
                        )
                    )
                ),
            )
        )

        val wordJump = WordDefinitionDBO(
            word = "jump",
            phonetic = WordPhoneticDBO(
                audio = null,
                text = "dʒʌmp"
            ),
            meanings = listOf(
                WordMeaningDBO(
                    partOfSpeech = "Verb",
                    synonyms = listOf("leap", "spring", "hop"),
                    antonyms = listOf("fall", "descend"),
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            "Push oneself off a surface and into the air by using the muscles in one's legs and feet.",
                            "He jumped over the fence to escape."
                        ),
                        WordMeaningDefinitionDBO(
                            "Move suddenly or involuntarily in reaction to a sudden sharp increase in difficulty or danger.",
                            "She jumped at the sudden noise behind her."
                        )
                    )
                ),
                WordMeaningDBO(
                    partOfSpeech = "Noun",
                    synonyms = listOf("leap", "spring", "hop"),
                    antonyms = listOf("fall", "descent"),
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            "An act of jumping from a surface.",
                            "The kangaroo made a high jump over the fence."
                        ),
                        WordMeaningDefinitionDBO(
                            "A sudden sharp increase in difficulty or danger.",
                            "There was a jump in the number of COVID-19 cases in the city."
                        )
                    )
                ),
            )
        )

        val wordSwim = WordDefinitionDBO(
            word = "swim",
            phonetic = WordPhoneticDBO(
                audio = null,
                text = "swɪm"
            ),
            meanings = listOf(
                WordMeaningDBO(
                    partOfSpeech = "Verb",
                    synonyms = listOf("dive", "plunge", "paddle"),
                    antonyms = listOf("sink", "drown"),
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            "Move through water by moving the body or parts of it through it.",
                            "She swims every day in the summer."
                        ),
                        WordMeaningDefinitionDBO(
                            "Propel oneself through water using the limbs.",
                            "The children love to swim in the pool."
                        )
                    )
                ),
                WordMeaningDBO(
                    partOfSpeech = "Noun",
                    synonyms = listOf("dive", "dip", "plunge"),
                    antonyms = listOf("sink", "drown"),
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            "An act of swimming.",
                            "She did a few laps in the pool for her daily swim."
                        ),
                        WordMeaningDefinitionDBO(
                            "A place or event where people can swim.",
                            "The community center has an indoor swim for residents."
                        )
                    )
                ),
            )
        )

        val wordEat = WordDefinitionDBO(
            word = "eat",
            phonetic = WordPhoneticDBO(
                audio = null,
                text = "it"
            ),
            meanings = listOf(
                WordMeaningDBO(
                    partOfSpeech = "Verb",
                    synonyms = listOf("consume", "ingest", "devour"),
                    antonyms = listOf("abstain", "refrain"),
                    definitions = listOf(
                        WordMeaningDefinitionDBO(
                            "Put (food) into the mouth and chew and swallow it.",
                            "He eats three meals a day."
                        ),
                        WordMeaningDefinitionDBO(
                            "Take (food) into the body by absorbing it.",
                            "The plant eats insects as its main source of nutrition."
                        )
                    )
                ),
            )
        )
    }
}
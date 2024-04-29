package dev.zotov.database.utils

import dev.zotov.database.entities.WordDBO

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
}
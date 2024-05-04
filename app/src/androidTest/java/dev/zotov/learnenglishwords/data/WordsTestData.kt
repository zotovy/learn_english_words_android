package dev.zotov.learnenglishwords.data

import dev.zotov.database.entities.WordDBO

object WordsTestData {

    object Dbos {
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
            wordEat,
            wordGreen,
            wordRed,
            wordTable,
            wordChair,
            wordSing,
            wordRead,
            wordSlowly,
            wordQuickly,
        )

        fun part1() = listOf(
            wordLight,
            wordHeavy,
            wordFast,
            wordSlow,
        )

        fun part2() = listOf(
            wordBig,
            wordCat,
            wordDog,
            wordBook,
        )

        fun part3() = listOf(
            wordRun,
            wordJump,
            wordSwim,
            wordEat,
        )

        fun part4() = listOf(
            wordGreen,
            wordRed,
            wordTable,
            wordChair,
        )

        fun part5() = listOf(
            wordSing,
            wordRead,
            wordSlowly,
            wordQuickly,
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

        val wordGreen = WordDBO(
            uid = 13,
            russian = "Зеленый",
            english = "Green",
            conjunction = "adjective"
        )

        val wordRed = WordDBO(
            uid = 14,
            russian = "Красный",
            english = "Red",
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

        val wordTable = WordDBO(
            uid = 15,
            russian = "Стол",
            english = "Table",
            conjunction = "noun"
        )

        val wordChair = WordDBO(
            uid = 16,
            russian = "Стул",
            english = "Chair",
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

        val wordSing = WordDBO(
            uid = 17,
            russian = "Петь",
            english = "Sing",
            conjunction = "verb"
        )

        val wordRead = WordDBO(
            uid = 18,
            russian = "Читать",
            english = "Read",
            conjunction = "verb"
        )

        // Adverbs
        val wordSlowly = WordDBO(
            uid = 19,
            russian = "Медленно",
            english = "Slowly",
            conjunction = "adverb"
        )

        val wordQuickly = WordDBO(
            uid = 20,
            russian = "Быстро",
            english = "Quickly",
            conjunction = "adverb"
        )
    }
}
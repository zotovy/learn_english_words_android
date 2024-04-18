package dev.zotov.words_data

import dev.zotov.database.AppDatabase
import dev.zotov.words_data.models.WordQuestion
import dev.zotov.words_data.utils.toWord
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

interface WordsRepository {

    suspend fun getFiveQuestions(): List<WordQuestion>
}

class WordsRepositoryImpl (private val appDatabase: AppDatabase): WordsRepository {
    override suspend fun getFiveQuestions(): List<WordQuestion> {
        val wordQuestions = coroutineScope {
            (1..5).map {
                async { getQuestion() }
            }
        }.awaitAll()

        return wordQuestions
    }

    private suspend fun getQuestion(): WordQuestion {
        val words = appDatabase.wordDao.getRandomWords(5)
        return WordQuestion(
            targetWord = words.first().toWord(),
            variants = words.subList(1, words.size - 1).map { it.toWord() }
        )
    }
}
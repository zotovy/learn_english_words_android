package dev.zotov.words_data

import dev.zotov.database.AppDatabase
import dev.zotov.words_api.WordsApi
import dev.zotov.words_data.models.WordDefinition
import dev.zotov.words_data.models.WordQuestion
import dev.zotov.words_data.utils.toWord
import dev.zotov.words_data.utils.toWordDefinition
import dev.zotov.words_data.utils.toWordDefinitionDBO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

interface WordsRepository {

    suspend fun getFiveQuestions(): List<WordQuestion>

    suspend fun getWordDefinition(word: String): WordDefinition?
}

class WordsRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val wordsApi: WordsApi
) : WordsRepository {
    override suspend fun getFiveQuestions(): List<WordQuestion> {
        val wordQuestions = coroutineScope {
            (1..5).map {
                async { getQuestion() }
            }
        }.awaitAll()

        return wordQuestions
    }

    override suspend fun getWordDefinition(word: String): WordDefinition? {
        // Cached
        val cached = getCachedWordDefinition(word)
        if (cached != null) {
            return cached
        }

        // Network
        val wordDefinition = getNetworkWordDefinition(word)
        if (wordDefinition != null) {
            saveWordDefinitionInToCache(wordDefinition)
            return wordDefinition
        }

        return null
    }

    private suspend fun getQuestion(): WordQuestion {
        val words = appDatabase.wordDao.getRandomWords(5)
        return WordQuestion(
            targetWord = words.first().toWord(),
            variants = words.subList(1, words.size - 1).map { it.toWord() }
        )
    }

    private suspend fun getCachedWordDefinition(word: String): WordDefinition? {
        val definition = appDatabase.wordDefinitionDao.getWordDefinition(word)

        if (definition.isEmpty()) {
            return null
        }

        return definition.first().toWordDefinition()
    }

    private suspend fun getNetworkWordDefinition(word: String): WordDefinition? {
        val response = wordsApi.getWordDefinition(word)

        if (response.isSuccessful) {
            val body = response.body()

            if (!body.isNullOrEmpty()) {
                return body.first().toWordDefinition()
            }
        }

        return null
    }

    private suspend fun saveWordDefinitionInToCache(definition: WordDefinition) {
        val wordDefinitionDBO = definition.toWordDefinitionDBO()
        appDatabase.wordDefinitionDao.insertWordDefinition(wordDefinitionDBO)
    }
}
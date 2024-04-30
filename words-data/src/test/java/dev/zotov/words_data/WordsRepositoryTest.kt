package dev.zotov.words_data

import dev.zotov.database.AppDatabase
import dev.zotov.database.dao.WordDao
import dev.zotov.database.dao.WordDefinitionDao
import dev.zotov.words_api.WordsApi
import dev.zotov.words_data.utils.TestData
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import retrofit2.Response


class WordsRepositoryTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var wordsApi: WordsApi
    private lateinit var wordDao: WordDao
    private lateinit var wordDefinitionDao: WordDefinitionDao
    private lateinit var wordsRepository: WordsRepository

    @Before
    fun setup() {
        wordDao = mock<WordDao>()
        wordDefinitionDao = mock<WordDefinitionDao>()
        appDatabase = mock<AppDatabase> {
            on { wordDao } doReturn wordDao
            on { wordDefinitionDao } doReturn wordDefinitionDao
        }
        wordsApi = mock<WordsApi>()
        wordsRepository = WordsRepositoryImpl(appDatabase, wordsApi)
    }

    @After
    fun teardown() {
        Mockito.reset(appDatabase)
        Mockito.reset(wordsApi)
        Mockito.reset(wordDao)
    }

    @Test
    fun shouldReturn5RandomQuestions() = runTest {
        `when`(wordDao.getRandomWords(4))
            .thenReturn(TestData.WordQuestions.wordQuestionsLightDbo)
            .thenReturn(TestData.WordQuestions.wordQuestionsRunDbo)
            .thenReturn(TestData.WordQuestions.wordQuestionsHeavyDbo)
            .thenReturn(TestData.WordQuestions.wordQuestionsDogDbo)
            .thenReturn(TestData.WordQuestions.wordQuestionsEatDbo)

        val questions = wordsRepository.getFiveQuestions()

        Assert.assertEquals(questions.size, 5)
        val expected = TestData.WordQuestions.fiveQuestions1()

        for (i in 0..4) {
            Assert.assertEquals(expected[i].targetWord, questions[i].targetWord)
            Assert.assertEquals(expected[i].variants.size, questions[i].variants.size)
            expected[i].variants.forEach {
                Assert.assertEquals(true, questions[i].variants.contains(it))
            }
        }

        verify(wordDao, times(5)).getRandomWords(4)
    }

    @Test
    fun shouldGetWordDefinitionFromNetworkIfNotCached() = runTest {
        `when`(wordDefinitionDao.getWordDefinition("to light"))
            .thenReturn(emptyList())
        `when`(wordsApi.getWordDefinition("light"))
            .thenReturn(Response.success(listOf(TestData.WordDefinitionDtos.wordLight)))

        val wordDefinition = wordsRepository.getWordDefinition("to light")

        Assert.assertEquals(TestData.WordDefinitions.wordLight, wordDefinition)

        verify(wordDefinitionDao, times(1)).getWordDefinition("to light")
        verify(wordsApi, times(1)).getWordDefinition("light")
        verify(wordDefinitionDao, times(1)).insertWordDefinition(TestData.WordDefinitionDBOS.wordLight)
    }

    @Test
    fun shouldGetWordDefinitionFromCacheIfExists() = runTest {
        `when`(wordDefinitionDao.getWordDefinition("to light"))
            .thenReturn(listOf(TestData.WordDefinitionDBOS.wordLight))

        val wordDefinition = wordsRepository.getWordDefinition("to light")

        Assert.assertEquals(TestData.WordDefinitions.wordLight, wordDefinition)

        verify(wordDefinitionDao, times(1)).getWordDefinition("to light")
        verify(wordsApi, times(0)).getWordDefinition(any())
    }

    @Test
    fun shouldCleanWordWithA() = runTest {
        `when`(wordDefinitionDao.getWordDefinition("a light"))
            .thenReturn(emptyList())
        `when`(wordsApi.getWordDefinition("light"))
            .thenReturn(Response.success(listOf(TestData.WordDefinitionDtos.wordLight)))

        wordsRepository.getWordDefinition("a light")

        verify(wordDefinitionDao, times(1)).getWordDefinition("a light")
        verify(wordsApi, times(1)).getWordDefinition("light")
    }

    @Test
    fun shouldCleanWordWithThe() = runTest {
        `when`(wordDefinitionDao.getWordDefinition("the light"))
            .thenReturn(emptyList())
        `when`(wordsApi.getWordDefinition("light"))
            .thenReturn(Response.success(listOf(TestData.WordDefinitionDtos.wordLight)))

        wordsRepository.getWordDefinition("the light")

        verify(wordDefinitionDao, times(1)).getWordDefinition("the light")
        verify(wordsApi, times(1)).getWordDefinition("light")
    }

    @Test
    fun shouldCleanWordWithManyParts() = runTest {
        `when`(wordDefinitionDao.getWordDefinition("the a light of"))
            .thenReturn(emptyList())
        `when`(wordsApi.getWordDefinition("light"))
            .thenReturn(Response.success(listOf(TestData.WordDefinitionDtos.wordLight)))

        wordsRepository.getWordDefinition("the a light of")

        verify(wordDefinitionDao, times(1)).getWordDefinition("the a light of")
        verify(wordsApi, times(1)).getWordDefinition("light")
    }
}
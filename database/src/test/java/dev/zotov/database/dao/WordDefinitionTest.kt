package dev.zotov.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dev.zotov.database.RoomAppDatabase
import dev.zotov.database.utils.TestData
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class WordDefinitionTest {

    private lateinit var database: RoomAppDatabase
    private lateinit var wordDefinitionDao: WordDefinitionDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RoomAppDatabase::class.java,
        ).allowMainThreadQueries().build()
        wordDefinitionDao = database.wordDefinitionDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun shouldFindExistingWord() = runTest {
        wordDefinitionDao.insertWordDefinition(TestData.WordDefinitionDBO.wordBig)
        wordDefinitionDao.insertWordDefinition(TestData.WordDefinitionDBO.wordDog)
        wordDefinitionDao.insertWordDefinition(TestData.WordDefinitionDBO.wordRun)

        val words = wordDefinitionDao.getWordDefinition("dog")

        Assert.assertEquals(words.size, 1)
        Assert.assertEquals(words.first(), TestData.WordDefinitionDBO.wordDog)
    }

    @Test
    fun shouldNotFindExistingWord() = runTest {
        wordDefinitionDao.insertWordDefinition(TestData.WordDefinitionDBO.wordBook)
        wordDefinitionDao.insertWordDefinition(TestData.WordDefinitionDBO.wordEat)
        wordDefinitionDao.insertWordDefinition(TestData.WordDefinitionDBO.wordSlow)

        val words = wordDefinitionDao.getWordDefinition("elephant")

        Assert.assertEquals(words.size, 0)
    }

    @Test
    fun shouldInsertWord() = runTest {
        wordDefinitionDao.insertWordDefinition(TestData.WordDefinitionDBO.wordSwim)

        val words = wordDefinitionDao.getWordDefinition(TestData.WordDefinitionDBO.wordSwim.word)

        Assert.assertEquals(words.size, 1)
        Assert.assertEquals(words.first(), TestData.WordDefinitionDBO.wordSwim)
    }

    @Test
    fun shouldInsertWordAndIgnoreConflict() = runTest {
        wordDefinitionDao.insertWordDefinition(TestData.WordDefinitionDBO.wordRun)
        wordDefinitionDao.insertWordDefinition(TestData.WordDefinitionDBO.wordRun)

        val words = wordDefinitionDao.getWordDefinition(TestData.WordDefinitionDBO.wordRun.word)

        Assert.assertEquals(words.size, 1)
        Assert.assertEquals(words.first(), TestData.WordDefinitionDBO.wordRun)
    }
}
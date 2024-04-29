package dev.zotov.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.zotov.database.RoomAppDatabase
import dev.zotov.database.utils.TestData
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WordDaoTest {

    private lateinit var database: RoomAppDatabase
    private lateinit var wordDao: WordDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RoomAppDatabase::class.java,
        ).allowMainThreadQueries().build()
        wordDao = database.wordDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun shouldReturn5RandomWord() = runTest {
        TestData.WordDBO.all().forEach { wordDao.insert(it) }

        val words = wordDao.getRandomWords(5)

        Assert.assertEquals(5, words.size)
        words.forEach {
            Assert.assertTrue(TestData.WordDBO.all().contains(it))
        }
    }

    @Test
    fun shouldReturn3RandomWord() = runTest {
        TestData.WordDBO.all().forEach { wordDao.insert(it) }

        val words = wordDao.getRandomWords(3)

        Assert.assertEquals(3, words.size)
        words.forEach {
            Assert.assertTrue(TestData.WordDBO.all().contains(it))
        }
    }

    @Test
    fun shouldReturnRandomWords() = runTest {
        TestData.WordDBO.all().forEach { wordDao.insert(it) }

        val words1 = wordDao.getRandomWords(2)
        val words2 = wordDao.getRandomWords(2)

        Assert.assertNotEquals(words1, words2)

        (words1 + words2).forEach {
            Assert.assertTrue(TestData.WordDBO.all().contains(it))
        }
    }
}
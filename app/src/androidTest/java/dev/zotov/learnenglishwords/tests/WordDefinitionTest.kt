package dev.zotov.learnenglishwords.tests

import androidx.test.core.app.ActivityScenario
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dev.zotov.database.AppDatabase
import dev.zotov.database.dao.WordDao
import dev.zotov.database.dao.WordDefinitionDao
import dev.zotov.learnenglishwords.MainActivity
import dev.zotov.learnenglishwords.data.WordsTestData
import dev.zotov.learnenglishwords.robots.WordDefinitionDialogFragmentRobot
import dev.zotov.learnenglishwords.robots.WordGameFragmentRobot
import dev.zotov.learnenglishwords.utils.MockDatabase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@HiltAndroidTest
class WordDefinitionTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private val wordDao: WordDao = mock()
    private val wordDefinitionDao: WordDefinitionDao = mock()

    @BindValue
    val database: AppDatabase = MockDatabase(wordDao, wordDefinitionDao)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testWordDefinition() = runTest {
        whenever(wordDao.getRandomWords(4))
            .thenReturn(WordsTestData.Dbos.part1())
            .thenReturn(WordsTestData.Dbos.part2())

        whenever(wordDefinitionDao.getWordDefinition(any()))
            .thenReturn(emptyList())

        ActivityScenario.launch(MainActivity::class.java)

        WordGameFragmentRobot.clickWord("Легкий")
        WordGameFragmentRobot.clickWord("Легкий")
        Thread.sleep(4_000)

        WordDefinitionDialogFragmentRobot.run {
            checkWord("Light")
            checkPhonetic("/laɪt/")
            checkPhoneticAudio()
            checkWordMeaning("noun")
        }
    }
}
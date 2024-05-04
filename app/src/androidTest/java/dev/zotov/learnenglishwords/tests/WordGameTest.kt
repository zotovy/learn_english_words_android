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
import dev.zotov.learnenglishwords.robots.WordGameFragmentRobot
import dev.zotov.learnenglishwords.robots.WordGameResultFragmentRobot
import dev.zotov.learnenglishwords.utils.MockDatabase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@HiltAndroidTest
class WordGameTest {

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
    fun testWordGameFlowScenario1() = runTest {
        whenever(wordDao.getRandomWords(4))
            .thenReturn(WordsTestData.Dbos.part1())
            .thenReturn(WordsTestData.Dbos.part2())
            .thenReturn(WordsTestData.Dbos.part3())
            .thenReturn(WordsTestData.Dbos.part4())
            .thenReturn(WordsTestData.Dbos.part5())

        ActivityScenario.launch(MainActivity::class.java)

        WordsTestData.Dbos.let { words ->
            WordGameFragmentRobot.run {
                // Step 1 – Correct
                checkTargetWord(words.wordLight.english)
                checkWordsVariants(words.part1().map { it.russian })
                clickWord(words.wordLight.russian)
                clickNext()

                // Step 2 – InCorrect
                checkTargetWord(words.wordBig.english)
                checkWordsVariants(words.part2().map { it.russian })
                clickWord(words.wordDog.russian)
                clickNext()

                // Step 3 – Skip
                checkTargetWord(words.wordRun.english)
                checkWordsVariants(words.part3().map { it.russian })
                clickSkip()

                // Step 4 – Correct
                checkTargetWord(words.wordGreen.english)
                checkWordsVariants(words.part4().map { it.russian })
                clickWord(words.wordGreen.russian)
                clickNext()

                // Step 5 – Skip
                checkTargetWord(words.wordSing.english)
                checkWordsVariants(words.part5().map { it.russian })
                clickSkip()
            }

            WordGameResultFragmentRobot.run {
                checkScore("2 / 5")
                checkResults("Light – ", "Легкий")
                checkResults("Big – ", "Собака")
                checkResults("Run")
                checkResults("Green – ", "Зеленый")
                checkResults("Sing")
                checkMessage("Ты можешь лучше!")
            }
        }
    }
}

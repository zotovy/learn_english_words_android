package dev.zotov.features.word_game.word_game

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.zotov.features.word_game.rules.MainCoroutineRule
import dev.zotov.features.word_game.utils.TestData
import dev.zotov.features.word_game.utils.getOrAwaitValueTest
import dev.zotov.words_data.WordsRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class WordGameViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    //
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val wordsRepository = mock<WordsRepositoryImpl>()
    private lateinit var wordGameViewModel: WordGameViewModel

    @Before
    fun setup() {
        wordGameViewModel = WordGameViewModel(wordsRepository)
    }

    @After
    fun teardown() {
        Mockito.reset(wordsRepository)
    }

    @Test
    fun shouldInitializeSuccessfully() = runTest {
        whenever(wordsRepository.getFiveQuestions())
            .thenReturn(TestData.WordQuestions.fiveQuestions1())

        wordGameViewModel.initialize()
        advanceUntilIdle()

        val state = wordGameViewModel.state.getOrAwaitValueTest()
        val expected = WordGameState.Idle(
            wordQuestions = TestData.WordQuestions.fiveQuestions1(),
            currentQuestionIndex = 0,
            currentQuestionState = WordVariantState.Idle,
        )
        Assert.assertEquals(expected, state)
    }

    @Test
    fun shouldInitializeWithErrorMessage() = runTest {
        whenever(wordsRepository.getFiveQuestions())
            .thenReturn(emptyList())

        wordGameViewModel.initialize()
        advanceUntilIdle()

        val state = wordGameViewModel.state.getOrAwaitValueTest()
        val expected = WordGameState.Error("Не удалось загрузить вопросы :(")
        Assert.assertEquals(expected, state)
    }

    @Test
    fun shouldInitializeWithErrorMessageIfWordsRepositoryThrowException() = runTest {
        whenever(wordsRepository.getFiveQuestions()).then {
            throw IOException()
        }

        wordGameViewModel.initialize()
        advanceUntilIdle()


        val state = wordGameViewModel.state.getOrAwaitValueTest()
        val expected = WordGameState.Error("Упс... Какая-то ошибка")
        Assert.assertEquals(expected, state)
    }
}
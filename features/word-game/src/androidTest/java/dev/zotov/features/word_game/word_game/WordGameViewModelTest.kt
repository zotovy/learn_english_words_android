package dev.zotov.features.word_game.word_game

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.zotov.features.word_game.models.WordGameResult
import dev.zotov.features.word_game.rules.MainCoroutineRule
import dev.zotov.features.word_game.utils.TestData
import dev.zotov.features.word_game.utils.getOrAwaitValueTest
import dev.zotov.words_data.WordsRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
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

    private suspend fun TestScope.initializeViewModel() {
        whenever(wordsRepository.getFiveQuestions())
            .thenReturn(TestData.WordQuestions.fiveQuestions1())

        wordGameViewModel.initialize()
        advanceUntilIdle()
    }

    private fun TestScope.getViewModelState(): WordGameState {
        advanceUntilIdle()
        return wordGameViewModel.state.getOrAwaitValueTest()
    }

    // ====================
    // Initialize
    // ====================

    @Test
    fun shouldInitializeSuccessfully() = runTest {
        whenever(wordsRepository.getFiveQuestions())
            .thenReturn(TestData.WordQuestions.fiveQuestions1())

        wordGameViewModel.initialize()

        val state = getViewModelState()
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

    // ====================
    // Skip Question
    // ====================

    @Test
    fun shouldSkipQuestion() = runTest {
        initializeViewModel()

        wordGameViewModel.skipQuestion()

        val expected = WordGameState.Idle(
            wordQuestions = TestData.WordQuestions.fiveQuestions1(),
            currentQuestionIndex = 1, // changed
            currentQuestionState = WordVariantState.Idle,
        )
        val state = getViewModelState()

        Assert.assertEquals(expected, state)

        val answers = wordGameViewModel.gameResult!!.answers
        val expectedAnswer = WordGameResult.Answer(
            english = TestData.Words.wordLight.english,
            russian = null,
            correct = false,
        )
        Assert.assertEquals(answers, listOf(expectedAnswer))
    }

    @Test
    fun shouldNotSkipQuestionIfStateIsNotIdle() = runTest {
        wordGameViewModel.skipQuestion()

        val expected = WordGameState.Loading
        val state = getViewModelState()

        Assert.assertEquals(expected, state)
    }

    @Test
    fun shouldNotSkipQuestionIfItsLastQuestion() = runTest {
        initializeViewModel()
        for (i in 1..4) {
            wordGameViewModel.skipQuestion()
            advanceUntilIdle()
        }

        val lastQuestionIndex = 4
        var state = getViewModelState() as WordGameState.Idle
        Assert.assertEquals(lastQuestionIndex, state.currentQuestionIndex)
        Assert.assertEquals(true, state.isLast)

        wordGameViewModel.skipQuestion()
        advanceUntilIdle()

        state = getViewModelState() as WordGameState.Idle
        Assert.assertEquals(lastQuestionIndex, state.currentQuestionIndex)
    }

    // ====================
    // Select variant
    // ====================

    @Test
    fun shouldSelectCorrectVariant() = runTest {
        initializeViewModel()

        wordGameViewModel.selectVariant(TestData.Words.wordLight)
        advanceUntilIdle()

        val expected = WordVariantState.Correct(TestData.Words.wordLight)
        val state = getViewModelState() as WordGameState.Idle
        Assert.assertEquals(expected, state.currentQuestionState)

        val expectedAnswer = WordGameResult.Answer(
            english = TestData.Words.wordLight.english,
            russian = TestData.Words.wordLight.russian,
            correct = true,
        )
        val answer = wordGameViewModel.gameResult!!.answers
        Assert.assertEquals(listOf(expectedAnswer), answer)
    }

    @Test
    fun shouldSelectInCorrectVariant() = runTest {
        initializeViewModel()

        wordGameViewModel.selectVariant(TestData.Words.wordRun)
        advanceUntilIdle()

        val expected = WordVariantState.InCorrect(TestData.Words.wordRun)
        val state = getViewModelState() as WordGameState.Idle
        Assert.assertEquals(expected, state.currentQuestionState)

        val expectedAnswer = WordGameResult.Answer(
            english = TestData.Words.wordLight.english,
            russian = TestData.Words.wordRun.russian,
            correct = false,
        )
        val answer = wordGameViewModel.gameResult!!.answers
        Assert.assertEquals(listOf(expectedAnswer), answer)
    }

    @Test
    fun shouldNotSelectCorrectVariantIfAlreadySelectOne() = runTest {
        initializeViewModel()

        wordGameViewModel.selectVariant(TestData.Words.wordLight)
        advanceUntilIdle()

        wordGameViewModel.selectVariant(TestData.Words.wordLight)
        advanceUntilIdle()

        val expected = WordVariantState.Correct(TestData.Words.wordLight)
        val state = getViewModelState() as WordGameState.Idle
        Assert.assertEquals(expected, state.currentQuestionState)

        val expectedAnswer = WordGameResult.Answer(
            english = TestData.Words.wordLight.english,
            russian = TestData.Words.wordLight.russian,
            correct = true,
        )
        val answer = wordGameViewModel.gameResult!!.answers
        Assert.assertEquals(listOf(expectedAnswer), answer)
    }

    @Test
    fun shouldNotSelectInCorrectVariantIfAlreadySelectOne() = runTest {
        initializeViewModel()

        wordGameViewModel.selectVariant(TestData.Words.wordLight)
        advanceUntilIdle()

        wordGameViewModel.selectVariant(TestData.Words.wordRun)
        advanceUntilIdle()

        val expected = WordVariantState.Correct(TestData.Words.wordLight)
        val state = getViewModelState() as WordGameState.Idle
        Assert.assertEquals(expected, state.currentQuestionState)

        val expectedAnswer = WordGameResult.Answer(
            english = TestData.Words.wordLight.english,
            russian = TestData.Words.wordLight.russian,
            correct = true,
        )
        val answer = wordGameViewModel.gameResult!!.answers
        Assert.assertEquals(listOf(expectedAnswer), answer)
    }
}
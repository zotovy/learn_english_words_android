package dev.zotov.features.word_game.word_game

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.zotov.features.word_game.models.WordGameResult
import dev.zotov.features.word_game.rules.MainCoroutineRule
import dev.zotov.features.word_game.utils.TestData
import dev.zotov.features.word_game.utils.getOrAwaitValueTest
import dev.zotov.words_data.WordsRepositoryImpl
import dev.zotov.words_data.models.Word
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

    // ====================
    // Word game result
    // ====================

    @Test
    fun shouldGetWordGameResultInIdleState() = runTest {
        initializeViewModel()

        // Correct 1, 2 questions, Incorrect 3, 4 questions, skip 5 question
        selectVariantAndNextQuestion(TestData.Words.wordLight)
        selectVariantAndNextQuestion(TestData.Words.wordRun)
        selectVariantAndNextQuestion(TestData.Words.wordFast)
        selectVariantAndNextQuestion(TestData.Words.wordJump)
        wordGameViewModel.skipQuestion()
        advanceUntilIdle()

        val gameResult = wordGameViewModel.gameResult

        val expectedGameResult = WordGameResult(
            score = 2,
            maxScore = 5,
            answers = listOf(
                WordGameResult.Answer(
                    english = TestData.Words.wordLight.english,
                    russian = TestData.Words.wordLight.russian,
                    correct = true,
                ),
                WordGameResult.Answer(
                    english = TestData.Words.wordRun.english,
                    russian = TestData.Words.wordRun.russian,
                    correct = true,
                ),
                WordGameResult.Answer(
                    english = TestData.Words.wordHeavy.english,
                    russian = TestData.Words.wordFast.russian,
                    correct = false,
                ),
                WordGameResult.Answer(
                    english = TestData.Words.wordDog.english,
                    russian = TestData.Words.wordJump.russian,
                    correct = false,
                ),
                WordGameResult.Answer(
                    english = TestData.Words.wordEat.english,
                    russian = null,
                    correct = false,
                ),
            )
        )

        Assert.assertEquals(expectedGameResult, gameResult)
    }

    @Test
    fun shouldReturnNullGameResultIfStateIsLoading() = runTest {
        val gameResult = wordGameViewModel.gameResult
        Assert.assertEquals(null, gameResult)
    }

    @Test
    fun shouldReturnNullGameResultIfStateIsError() = runTest {
        initializeViewModelWithError()
        val gameResult = wordGameViewModel.gameResult
        Assert.assertEquals(null, gameResult)
    }

    // ====================
    // Helpers
    // ====================

    private suspend fun TestScope.initializeViewModel() {
        whenever(wordsRepository.getFiveQuestions())
            .thenReturn(TestData.WordQuestions.fiveQuestions1())

        wordGameViewModel.initialize()
        advanceUntilIdle()
    }

    private suspend fun TestScope.initializeViewModelWithError() {
        whenever(wordsRepository.getFiveQuestions()).thenReturn(null)
        wordGameViewModel.initialize()
        advanceUntilIdle()
    }

    private fun TestScope.getViewModelState(): WordGameState {
        advanceUntilIdle()
        return wordGameViewModel.state.getOrAwaitValueTest()
    }

    private fun TestScope.selectVariantAndNextQuestion(word: Word) {
        wordGameViewModel.selectVariant(word)
        advanceUntilIdle()
        wordGameViewModel.nextQuestion()
        advanceUntilIdle()
    }
}

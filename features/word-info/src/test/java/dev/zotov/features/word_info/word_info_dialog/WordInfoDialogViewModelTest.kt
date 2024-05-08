package dev.zotov.features.word_info.word_info_dialog

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.zotov.features.word_info.word_info_dialog.rules.MainCoroutineRule
import dev.zotov.features.word_info.word_info_dialog.utils.TestData
import dev.zotov.features.word_info.word_info_dialog.utils.getOrAwaitValueTest
import dev.zotov.shared.services.SoundPlayerServiceImpl
import dev.zotov.words_data.WordsRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
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
import org.robolectric.RobolectricTestRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
class WordInfoDialogViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val wordsRepository = mock<WordsRepositoryImpl>()
    private val soundPlayerService = mock<SoundPlayerServiceImpl>()

    private lateinit var viewModel: WordInfoDialogViewModel

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule(testDispatcher)

    @Before
    fun setup() {
        viewModel = WordInfoDialogViewModel(wordsRepository, soundPlayerService, testDispatcher)
    }

    @After
    fun teardown() {
        Mockito.reset(wordsRepository)
        Mockito.reset(soundPlayerService)
    }

    @Test
    fun shouldInitializeSuccessfully() = runTest {
        whenever(wordsRepository.getWordDefinition("light"))
            .thenReturn(TestData.WordDefinitions.wordLight)

        viewModel.initialize("light")

        val state = getViewModelState()
        val expected = WordInfoDialogState.Idle(TestData.WordDefinitionUiModels.wordLight)

        Assert.assertEquals(expected, state)
    }

    private fun TestScope.getViewModelState(): WordInfoDialogState {
        advanceUntilIdle()
        return viewModel.state.getOrAwaitValueTest()
    }
}

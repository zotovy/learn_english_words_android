package dev.zotov.features.word_game

import dev.zotov.words_data.models.Word
import dev.zotov.words_data.models.WordQuestion

sealed class WordGameState {
    data object Loading: WordGameState()

    data class Idle(
        val wordQuestions: List<WordQuestion>,
        val currentQuestionIndex: Int,
        val currentQuestionState: WordVariantState,
    ): WordGameState() {
        val currentQuestion: WordQuestion get() = wordQuestions[currentQuestionIndex]
    }

    data object Error: WordGameState()
}

sealed class WordVariantState {
    data object Idle: WordVariantState()
    data class Correct(val word: Word): WordVariantState()
    data class InCorrect(val word: Word): WordVariantState()
}
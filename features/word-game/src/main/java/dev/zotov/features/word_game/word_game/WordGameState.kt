package dev.zotov.features.word_game.word_game

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

        val isLast: Boolean get() = currentQuestionIndex == wordQuestions.size - 1
    }

    data class Error(val message: String): WordGameState()
}

sealed class WordVariantState {
    data object Idle: WordVariantState()
    data class Correct(val word: Word): WordVariantState()
    data class InCorrect(val word: Word): WordVariantState()
}
package dev.zotov.features.word_info.word_info_dialog

import dev.zotov.features.word_info.models.WordDefinitionUiModel

internal sealed class WordInfoDialogState {
    data object Loading: WordInfoDialogState()

    data class Idle(
        val wordInfo: WordDefinitionUiModel
    ): WordInfoDialogState()

    data class Error(
        val word: String,
        val message: String
    ): WordInfoDialogState()
}
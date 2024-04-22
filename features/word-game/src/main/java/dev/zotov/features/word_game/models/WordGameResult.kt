package dev.zotov.features.word_game.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WordGameResult(
    val score: Int,
    val maxScore: Int,
    val answers: List<Answer>
) : Parcelable {
    @Parcelize
    data class Answer(
        val english: String,
        val russian: String?,
        val correct: Boolean
    ): Parcelable
}


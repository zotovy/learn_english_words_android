package dev.zotov.shared.navigation

import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest

object DeepLinks {

    private fun buildNavRequest(uriString: String) = NavDeepLinkRequest.Builder
        .fromUri(uriString.toUri())
        .build()

    fun wordInfoDialog(word: String) =
        buildNavRequest("${Uris.WORD_INFO_DIALOG}?word=${word}")

    object Uris {
        const val WORD_INFO_DIALOG =
            "android-app://dev.zotov.learn_english_words/features/word-info/word-info-dialog"
    }
}

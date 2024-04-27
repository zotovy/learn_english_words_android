package dev.zotov.words_data.models

data class WordPhonetic(
    val audio: String?,
    val text: String?,
) {
    val notEmpty: Boolean get() = text != null
}
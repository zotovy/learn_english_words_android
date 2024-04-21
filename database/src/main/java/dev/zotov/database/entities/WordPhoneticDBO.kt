package dev.zotov.database.entities

import androidx.room.ColumnInfo

data class WordPhoneticDBO(
    @ColumnInfo(name = "audio") val audio: String?,
    @ColumnInfo(name = "text") val text: String?,
)

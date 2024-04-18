package dev.zotov.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word(
    @PrimaryKey val uid: Long,
    @ColumnInfo(name = "english") val english: String,
    @ColumnInfo(name = "russian") val russian: String,
    @ColumnInfo(name = "conjunction") val conjunction: String,
)

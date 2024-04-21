package dev.zotov.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_definition")
data class WordDefinitionDBO(
    @PrimaryKey @ColumnInfo(name = "word") val word: String,

    @Embedded val phonetic: WordPhoneticDBO?,

    @ColumnInfo(name = "meanings")
    val meanings: List<WordMeaningDBO>,
)
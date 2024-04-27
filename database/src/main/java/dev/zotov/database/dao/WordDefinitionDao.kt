package dev.zotov.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import dev.zotov.database.entities.WordDefinitionDBO

@Dao
interface WordDefinitionDao {

    @Transaction
    @Query("SELECT * FROM word_definition WHERE word = (:word) LIMIT 1")
    suspend fun getWordDefinition(word: String): List<WordDefinitionDBO>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWordDefinition(wordDefinition: WordDefinitionDBO)
}
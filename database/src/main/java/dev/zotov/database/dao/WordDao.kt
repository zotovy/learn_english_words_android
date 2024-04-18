package dev.zotov.database.dao

import androidx.room.Dao
import androidx.room.Query
import dev.zotov.database.entities.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM words ORDER BY Random() LIMIT (:limit)")
    suspend fun getRandomWords(limit: Int): List<Word>
}
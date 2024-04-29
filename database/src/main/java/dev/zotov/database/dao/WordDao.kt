package dev.zotov.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.zotov.database.entities.WordDBO

@Dao
interface WordDao {

    @Query("SELECT * FROM words ORDER BY Random() LIMIT (:limit)")
    suspend fun getRandomWords(limit: Int): List<WordDBO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(wordDBO: WordDBO)
}
package dev.zotov.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.zotov.database.dao.WordDao
import dev.zotov.database.entities.Word

class AppDatabase internal constructor(private val database: RoomAppDatabase) {
    val wordDao: WordDao
        get() = database.wordDao()
}


@Database(entities = [Word::class], version = 1)
internal abstract class RoomAppDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao
}


fun AppDatabase(applicationContext: Context): AppDatabase {
    val roomDatabase = Room.databaseBuilder(
        applicationContext,
        RoomAppDatabase::class.java,
        "english-words-database"
    )
        .createFromAsset("database/words.db")
        .build()

    return AppDatabase(database = roomDatabase)
}
package dev.zotov.database

import android.content.Context
import androidx.room.Room
import dev.zotov.database.dao.WordDao
import dev.zotov.database.dao.WordDefinitionDao

interface AppDatabase {
    val wordDao: WordDao
    val wordDefinitionDao: WordDefinitionDao
    fun clearAll()
}

class AppDatabaseImpl internal constructor(private val database: RoomAppDatabase): AppDatabase {
    override val wordDao: WordDao
        get() = database.wordDao()

    override val wordDefinitionDao: WordDefinitionDao
        get() = database.wordDefinitionDao()

    override fun clearAll() = database.clearAllTables()
}


fun AppDatabase(applicationContext: Context): AppDatabase {
    val roomDatabase = Room.databaseBuilder(
        applicationContext,
        RoomAppDatabase::class.java,
        "english-words-database"
    )
        .createFromAsset("database/words.db")
        .build()

    return AppDatabaseImpl(database = roomDatabase)
}

fun createInMemoryAppDatabase(applicationContext: Context): AppDatabase {
    val roomDatabase = Room.inMemoryDatabaseBuilder(
        applicationContext,
        RoomAppDatabase::class.java,
    )
        .allowMainThreadQueries()
        .build()

    return AppDatabaseImpl(database = roomDatabase)
}

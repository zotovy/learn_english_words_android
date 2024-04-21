package dev.zotov.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.zotov.database.converters.WordMeaningDBOTypeConverter
import dev.zotov.database.dao.WordDao
import dev.zotov.database.dao.WordDefinitionDao
import dev.zotov.database.entities.WordDBO
import dev.zotov.database.entities.WordDefinitionDBO

class AppDatabase internal constructor(private val database: RoomAppDatabase) {
    val wordDao: WordDao
        get() = database.wordDao()

    val wordDefinitionDao: WordDefinitionDao
        get() = database.wordDefinitionDao()
}


@Database(
    entities = [
        WordDBO::class,
        WordDefinitionDBO::class
    ],
    version = 1
)
@TypeConverters(WordMeaningDBOTypeConverter::class)
internal abstract class RoomAppDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    abstract fun wordDefinitionDao(): WordDefinitionDao
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
package dev.zotov.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.zotov.database.converters.WordMeaningDBOTypeConverter
import dev.zotov.database.dao.WordDao
import dev.zotov.database.dao.WordDefinitionDao
import dev.zotov.database.entities.WordDBO
import dev.zotov.database.entities.WordDefinitionDBO

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
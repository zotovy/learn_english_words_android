package dev.zotov.database.converters

import androidx.room.TypeConverter
import dev.zotov.database.entities.WordMeaningDBO
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class WordMeaningDBOTypeConverter {
    @TypeConverter
    fun convertToJsonString(wordMeanings: List<WordMeaningDBO>): String {
        return Json.encodeToString(wordMeanings)
    }

    @TypeConverter
    fun convertToObject(json: String): List<WordMeaningDBO> {
        return Json.decodeFromString(json)
    }
}
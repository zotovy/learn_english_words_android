package dev.zotov.learnenglishwords.utils

import dev.zotov.database.AppDatabase
import dev.zotov.database.dao.WordDao
import dev.zotov.database.dao.WordDefinitionDao


class MockDatabase(
    override val wordDao: WordDao,
    override val wordDefinitionDao: WordDefinitionDao
) : AppDatabase {

    override fun clearAll() {}
}
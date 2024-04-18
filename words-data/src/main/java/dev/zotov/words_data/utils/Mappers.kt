package dev.zotov.words_data.utils

import dev.zotov.database.entities.WordDBO
import dev.zotov.words_data.models.Word

fun WordDBO.toWord(): Word {
    return Word(
        id = this.uid,
        russian = this.russian,
        english = this.english.split(Regex("[;,]")).first(),
        conjunction = this.conjunction,
    )
}


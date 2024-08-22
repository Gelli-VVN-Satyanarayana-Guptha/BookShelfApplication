package com.example.bookshelf.data.local.books.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bookshelf.domain.model.TagData

@Entity(tableName = "tags")
data class TagEntity(
    @PrimaryKey
    val bookId : String,
    val tagsList : List<String>
) {
    fun toTagData() : TagData {
        return TagData (
            bookId = bookId,
            tagsList = tagsList
        )
    }
}
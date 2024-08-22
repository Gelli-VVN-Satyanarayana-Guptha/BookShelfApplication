package com.example.bookshelf.data.local.books.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.bookshelf.domain.model.TagData

data class BookWithTags(
    @Embedded val book: BookEntity,
    @Relation(
        parentColumn = "bookId",
        entityColumn = "tagId",
        associateBy = Junction(BookTagCrossRef::class)
    )
    val tags: List<TagEntity>
){
    fun getListOfTags() : List<TagData> {
        return tags.map { it.toTagData() }
    }
}
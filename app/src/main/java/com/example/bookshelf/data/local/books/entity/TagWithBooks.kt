package com.example.bookshelf.data.local.books.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.bookshelf.domain.model.BookData

data class TagWithBooks(
    @Embedded val tag: TagEntity,
    @Relation(
        parentColumn = "tagId",
        entityColumn = "bookId",
        associateBy = Junction(BookTagCrossRef::class)
        )
    val books: List<BookEntity>
) {
    fun getListOfBooks() : List<BookData> {
        return books.map { it.toBookData() }
    }
}

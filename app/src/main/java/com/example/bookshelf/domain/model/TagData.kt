package com.example.bookshelf.domain.model

import com.example.bookshelf.data.local.books.entity.TagEntity

data class TagData(
    val bookId: String,
    val tagsList: List<String>
) {
    fun toTagEntity(): TagEntity {
        return TagEntity(
            bookId = bookId,
            tagsList = tagsList
        )
    }
}

package com.example.bookshelf.data.local.books.entity

import androidx.room.Entity

@Entity(primaryKeys = ["bookId", "tagId"])
data class BookTagCrossRef(
    val bookId: String,
    val tagId: String
)

package com.example.bookshelf.data.local.books.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bookshelf.domain.model.BookData
import com.example.bookshelf.presentation.screens.utils.DateTimeUtils

@Entity(tableName = "books")
data class BookEntity (
    @PrimaryKey
    val bookId: String,
    val image: String?,
    val score: Double,
    val popularity: Int,
    val title: String,
    val publishedChapterDate: Long?,
){
    fun toBookData() : BookData {
        return BookData(
            bookId = bookId,
            image = image,
            score = score,
            popularity = popularity,
            title = title,
            publishedChapterDate = DateTimeUtils.epochToLocalDateTime(publishedChapterDate)
        )
    }
}

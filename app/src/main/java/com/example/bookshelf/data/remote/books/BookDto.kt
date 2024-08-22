package com.example.bookshelf.data.remote.books

import com.example.bookshelf.data.local.books.entity.BookEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookDto(
    @SerialName("id"                   ) var bookId               : String,
    @SerialName("image"                ) var image                : String? = null,
    @SerialName("score"                ) var score                : Double? = null,
    @SerialName("popularity"           ) var popularity           : Int?    = null,
    @SerialName("title"                ) var title                : String? = null,
    @SerialName("publishedChapterDate" ) var publishedChapterDate : Long?    = null
) {
    fun toBookEntity() : BookEntity {
        return BookEntity(
            bookId = bookId,
            image = image,
            score = score ?: 0.0,
            popularity = popularity ?: 0,
            title = title ?: "Not Found",
            publishedChapterDate = publishedChapterDate
        )
    }
}

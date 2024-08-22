package com.example.bookshelf.domain.model

import java.time.LocalDateTime

data class BookData(
    val bookId: String,
    val image: String?,
    val score: Double,
    val popularity: Int,
    val title: String,
    val isFavorite: Boolean = true,
    val publishedChapterDate: LocalDateTime?,
    val description: String = "lorem ipsum dolor sit amet lorem ipsum dolor sit amet lorem ipsum dolor sit amet",
    var tags: List<String> = listOf("satya", "must read", "novel", "adventure", "fantasy", "read again")
)

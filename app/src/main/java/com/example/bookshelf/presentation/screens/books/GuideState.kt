package com.example.bookshelf.presentation.screens.books

import com.example.bookshelf.domain.model.BookData

data class GuideState (
    val bookId: String = "",
    val books: List<BookData> = listOf(),
    var booksByYear: Map<Int,List<BookData>> = mapOf(),
    var openDetails: Boolean = false,
    var openBottomSheet: Boolean = false,
    var filterState: Boolean = false,
    var addPlaylist: Boolean = false,
    var filterApplied: Boolean = false
)
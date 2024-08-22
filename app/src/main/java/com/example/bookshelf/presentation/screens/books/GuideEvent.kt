package com.example.bookshelf.presentation.screens.books

sealed interface GuideEvent {
    data object LoadBooksData : GuideEvent
}
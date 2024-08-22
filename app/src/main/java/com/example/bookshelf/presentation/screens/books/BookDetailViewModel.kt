package com.example.bookshelf.presentation.screens.books

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.domain.model.BookData
import com.example.bookshelf.domain.repository.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val booksRepo: BooksRepository
) : ViewModel() {
    // State to hold the selected book data
    var selectedBook = mutableStateOf<BookData?>(null)
    private var isFavorite = mutableStateOf(false)

    fun selectBook(id: String) {
        viewModelScope.launch {
            selectedBook.value = booksRepo.getBookById(id)
            isFavorite.value = selectedBook.value?.isFavorite == true
        }
    }

    fun toggleFavorite() {
        isFavorite.value = !isFavorite.value
    }

    fun addTagToBook(tag: String) {
        selectedBook.value?.let {
            viewModelScope.launch {
               // booksRepo.addTagToBook(it.bookId, tag)
            }
            it.tags += tag
        }
    }
}
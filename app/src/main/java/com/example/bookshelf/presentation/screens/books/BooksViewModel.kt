package com.example.bookshelf.presentation.screens.books

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.bookshelf.data.local.books.entity.BookEntity
import com.example.bookshelf.data.remote.books.BooksApi
import com.example.bookshelf.domain.model.BookData
import com.example.bookshelf.domain.repository.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val booksApi: BooksApi,
    private val pager : Pager<Int, BookEntity>,
    private val booksRepo : BooksRepository,
) : ViewModel() {
    val books = mutableListOf<BookData>()
    var tabYears = mutableListOf<Int>()
    var selectedYear = mutableIntStateOf(-1)

    val isLoading = mutableStateOf(false)

    init {
        if (books.isEmpty()) {
            isLoading.value = true
            loadBooksAndTabYears()
        }
    }

    // pagination logic
    val booksPagingFlow = pager.flow
        .map { pagingData ->
            pagingData.map { it.toBookData() }
        }
        .cachedIn(viewModelScope)

    private val _state = MutableStateFlow(GuideState())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), GuideState())

    fun onEvent(event: GuideEvent){
        when(event) {

            GuideEvent.LoadBooksData -> {
                viewModelScope.launch {
                    
                }
            }

        }
    }

    private fun loadBooksAndTabYears() {
        viewModelScope.launch {
            books.addAll(
                booksApi.getBooks().map {
                    it.toBookEntity().toBookData()
                }
            )
            getTabYears()
            isLoading.value = false
        }
    }

    private fun getTabYears() {
        tabYears.addAll(
            books.map {
                Log.d(TAG, "Years: ${it.publishedChapterDate?.year}")
                it.publishedChapterDate?.year ?: 0
            }.distinct().sorted()
        )
        selectedYear.intValue = tabYears.first()
    }

    companion object {
        const val TAG = "BooksViewModel"
    }
}
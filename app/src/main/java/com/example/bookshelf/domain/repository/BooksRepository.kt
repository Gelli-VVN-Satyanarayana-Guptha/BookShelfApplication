package com.example.bookshelf.domain.repository

import com.example.bookshelf.data.local.books.entity.BookEntity
import com.example.bookshelf.domain.model.BookData
import kotlinx.coroutines.flow.Flow

interface BooksRepository {

    suspend fun insertBooks(books : List<BookEntity>)

    fun getBooks() : Flow<List<BookData>>

    suspend fun getBookById(bookId : String) : BookData?

    fun searchBooks(query : String) : Flow<List<BookData>>

    suspend fun clearBooks()

}
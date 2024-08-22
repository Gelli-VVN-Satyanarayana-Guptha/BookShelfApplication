package com.example.bookshelf.data.local.books

import com.example.bookshelf.data.local.books.entity.BookEntity
import com.example.bookshelf.domain.model.BookData
import com.example.bookshelf.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BooksRepositoryImpl(
    private val dao : BooksDao
): BooksRepository {

    override suspend fun insertBooks(books: List<BookEntity>) {
        dao.upsertAll(books)
    }

    override fun getBooks() : Flow<List<BookData>> {
        return dao.getBooks().map {
            books -> books.map { it.toBookData() }
        }
    }

    override suspend fun getBookById(bookId: String) : BookData? {
        return dao.getBookById(bookId)?.toBookData()
    }

    override fun searchBooks(query: String): Flow<List<BookData>> {
        return dao.searchBooks(query).map {
            books -> books.map { it.toBookData() }
        }
    }

    override suspend fun clearBooks() {
        dao.clearAll()
    }
}
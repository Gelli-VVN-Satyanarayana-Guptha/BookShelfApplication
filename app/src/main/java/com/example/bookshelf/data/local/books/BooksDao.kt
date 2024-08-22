package com.example.bookshelf.data.local.books

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.bookshelf.data.local.books.entity.BookEntity
import com.example.bookshelf.data.local.books.entity.BookTagCrossRef
import com.example.bookshelf.data.local.books.entity.TagEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {

    @Upsert(entity = BookEntity::class)
    suspend fun upsertAll(books : List<BookEntity>)

    @Query("SELECT * FROM books")
    fun pagingSource() : PagingSource<Int, BookEntity>

    @Query("SELECT * FROM books")
    fun getBooks() : Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE bookId = :bookId")
    suspend fun getBookById(bookId: String): BookEntity?

    @Query("SELECT * FROM books WHERE title LIKE '%' || :query || '%'")
    fun searchBooks(query: String): Flow<List<BookEntity>>

    @Query("DELETE FROM books")
    suspend fun clearAll()

    @Transaction
    @Query("SELECT * FROM tags WHERE bookId = :bookId")
    suspend fun getTagsOfBook(bookId: String): TagEntity?

    @Insert(entity = TagEntity::class)
    suspend fun addTagToBook(tagEntity: TagEntity)

    /** Need to Be Removed **/

    @Insert(entity = BookTagCrossRef::class,onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTagToBook(bookTagCrossRef: BookTagCrossRef)

}
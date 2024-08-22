package com.example.bookshelf.data.local.books

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bookshelf.data.local.Converters
import com.example.bookshelf.data.local.books.entity.BookEntity
import com.example.bookshelf.data.local.books.entity.BookTagCrossRef
import com.example.bookshelf.data.local.books.entity.TagEntity

@Database(
    entities = [
        BookEntity::class,
        TagEntity::class,
        BookTagCrossRef::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class BooksDatabase : RoomDatabase() {
    abstract val dao : BooksDao
}
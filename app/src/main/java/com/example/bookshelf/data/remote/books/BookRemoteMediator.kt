package com.example.bookshelf.data.remote.books

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.bookshelf.data.local.books.BooksDatabase
import com.example.bookshelf.data.local.books.entity.BookEntity
import java.io.IOException


@OptIn(ExperimentalPagingApi::class)
class BookRemoteMediator (
    private val booksDb : BooksDatabase,
    private val booksApi: BooksApi
) : RemoteMediator<Int, BookEntity>() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BookEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType){
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
            }

            val books = booksApi.getBooks()

            booksDb.withTransaction {
                if(loadType == LoadType.REFRESH){
                    booksDb.dao.clearAll()
                }
                val booksEntities = books.map{ it.toBookEntity() }
                booksDb.dao.upsertAll(booksEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = books.isEmpty()
            )

        }catch (e : IOException){
            MediatorResult.Error(e)
        }catch (e : HttpException){
            MediatorResult.Error(e)
        }
    }
}

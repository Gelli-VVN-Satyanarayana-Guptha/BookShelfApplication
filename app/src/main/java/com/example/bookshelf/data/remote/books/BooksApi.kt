package com.example.bookshelf.data.remote.books

import retrofit2.http.GET

interface BooksApi {

    @GET("b/CNGI")
    suspend fun getBooks() : List<BookDto>

    companion object{
        const val BASE_URL = "https://jsonkeeper.com/"
    }
}
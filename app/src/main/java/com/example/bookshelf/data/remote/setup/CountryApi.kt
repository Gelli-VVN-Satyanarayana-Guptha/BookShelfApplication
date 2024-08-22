package com.example.bookshelf.data.remote.setup

import retrofit2.http.GET

interface CountryApi {
    @GET("countries")
    suspend fun getCountries() : CountriesDto

    companion object{
        const val BASE_URL = "https://api.first.org/data/v1/"
    }
}
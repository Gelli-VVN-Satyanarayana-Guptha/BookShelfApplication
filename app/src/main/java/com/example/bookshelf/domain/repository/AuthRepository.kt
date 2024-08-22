package com.example.bookshelf.domain.repository

interface AuthRepository {

    fun setLoggedInStatus(isLoggedIn: Boolean)

    fun getLoggedInStatus(): Boolean

    fun login(email: String, password: String): Boolean

    fun signUp(email: String, password: String, country: String)

}
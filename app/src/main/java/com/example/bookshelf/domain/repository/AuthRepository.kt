package com.example.bookshelf.domain.repository

interface AuthRepository {

    fun setLoggedInStatus(isLoggedIn: Boolean)

    fun getLoggedInStatus(): Boolean

}
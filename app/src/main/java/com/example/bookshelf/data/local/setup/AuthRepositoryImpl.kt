package com.example.bookshelf.data.local.setup

import android.content.SharedPreferences
import com.example.bookshelf.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor (
    private val sharedPreferences: SharedPreferences
) : AuthRepository {

    override fun setLoggedInStatus(isLoggedIn: Boolean) {
        sharedPreferences.edit().putBoolean("isLoggedIn", isLoggedIn).apply()
    }

    override fun getLoggedInStatus(): Boolean {
        return sharedPreferences.getBoolean("isLoggedIn", true)
    }

}
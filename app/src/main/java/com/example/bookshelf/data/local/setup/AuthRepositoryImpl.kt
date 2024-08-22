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
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

    override fun login(email: String, password: String): Boolean {
        val savedEmail = sharedPreferences.getString("email", null)
        val savedPassword = sharedPreferences.getString("password", null)
        if (savedEmail == email && savedPassword == password) {
            setLoggedInStatus(true)
            return true
        }
        return false
    }

    override fun signUp(email: String, password: String, country: String) {
        sharedPreferences.edit().putString("email", email).apply()
        sharedPreferences.edit().putString("password", password).apply()
        sharedPreferences.edit().putString("country", country).apply()
    }


}
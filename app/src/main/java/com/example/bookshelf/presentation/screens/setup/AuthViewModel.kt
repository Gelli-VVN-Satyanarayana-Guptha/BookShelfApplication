package com.example.bookshelf.presentation.screens.setup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.data.remote.setup.CountryApi
import com.example.bookshelf.domain.repository.AuthRepository
import com.example.bookshelf.presentation.screens.utils.PasswordUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val countryApi: CountryApi,
    private val authRepo : AuthRepository,
) : ViewModel() {
    val countriesList = mutableListOf<String>()

    fun setCountriesList() {
       viewModelScope.launch {
           countryApi.getCountries().data?.map {
               countriesList.add(it.value.country)
           }
       }
    }

    fun checkLoginStatus(): Boolean {
        return authRepo.getLoggedInStatus()
    }

    fun setLoginStatus(status: Boolean) {
        authRepo.setLoggedInStatus(status)
    }

    fun login(email: String, password: String) : Boolean {
        return authRepo.login(email, password)
    }

    fun signUp(email: String, password: String, country: String) : Boolean {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            if (PasswordUtils.isValidPassword(password)) {
                authRepo.signUp(email, password, country)
                return true
            }
        }
        return false
    }
}
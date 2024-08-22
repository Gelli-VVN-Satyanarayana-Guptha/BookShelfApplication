package com.example.bookshelf.presentation.screens.setup

import androidx.lifecycle.ViewModel
import com.example.bookshelf.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepo : AuthRepository,
) : ViewModel() {

    fun checkLoginStatus(): Boolean {
        return authRepo.getLoggedInStatus()
    }

    fun setLoginStatus(status: Boolean) {
        authRepo.setLoggedInStatus(status)
    }
}
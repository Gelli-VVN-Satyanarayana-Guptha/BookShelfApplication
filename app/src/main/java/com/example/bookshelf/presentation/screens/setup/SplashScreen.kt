package com.example.bookshelf.presentation.screens.setup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen (
    navController: NavController
) {
    val viewModel = hiltViewModel<AuthViewModel>()

    LaunchedEffect(Unit) {
        delay(2000)
        if (viewModel.checkLoginStatus()) {
            navController.navigate("guide")
        } else {
            navController.navigate("login")
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Text (
            modifier = Modifier.align(Alignment.Center),
            text = "Bookshelf",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Black
        )
    }
}
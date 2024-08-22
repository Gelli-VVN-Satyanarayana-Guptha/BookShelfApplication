package com.example.bookshelf.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookshelf.presentation.screens.books.BookDetailScreen
import com.example.bookshelf.presentation.screens.books.BooksGuideScreen
import com.example.bookshelf.presentation.screens.setup.LoginScreen
import com.example.bookshelf.presentation.screens.setup.SignUpScreen
import com.example.bookshelf.presentation.screens.setup.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {

        composable("splash") {
            SplashScreen(navController)
        }

        composable("login") {
            LoginScreen(navController)
        }

        composable("signup") {
            SignUpScreen(navController)
        }

        composable("guide") {
            BooksGuideScreen(navController)
        }

        composable("detail/{bookId}") {
            it.arguments?.getString("bookId")?.let { bookId ->
                BookDetailScreen(
                    bookId,
                    navController
                )
            }
        }
    }
}




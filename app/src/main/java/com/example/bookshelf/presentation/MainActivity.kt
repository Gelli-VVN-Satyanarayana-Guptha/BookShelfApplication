package com.example.bookshelf.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.bookshelf.domain.model.BookData
import com.example.bookshelf.presentation.screens.AppNavigation
import com.example.bookshelf.presentation.ui.theme.BookShelfTheme
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookShelfTheme {

                AppNavigation()

                val book = BookData (
                    bookId = "jgiieojr234",
                    title = "The Great Gatsby",
                    publishedChapterDate = LocalDateTime.now(),
                    image = "https://cdn.myanimelist.net/images/anime/6/73245.jpg",
                    score = 20.5,
                    popularity = 543562
                )
                // BooksGuideScreen()
                // BookDetailScreen()
            }
        }
    }
}
package com.example.bookshelf.presentation.screens.books

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.bookshelf.presentation.screens.books.widgets.BookCard

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksGuideScreen(
    navController: NavController
) {

    val context = LocalContext.current

    val viewModel = hiltViewModel<BooksViewModel>()
    val books = viewModel.booksPagingFlow.collectAsLazyPagingItems()
    val state by viewModel.state.collectAsState()

    LaunchedEffect (key1 = books.loadState) {
        if(books.loadState.refresh is LoadState.Error){
            Toast.makeText(
                context,
                "Error: " + (books.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    MaterialTheme {
        Scaffold (
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Book Shelf",
                            textAlign = TextAlign.Center
                        )
                    },
                    colors = topAppBarColors(containerColor = Color.Cyan)
                )
            },
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                if (viewModel.isLoading.value) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                } else {
                    Column (
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LazyRow (
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.Cyan),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            item {
                                Spacer(modifier = Modifier.width(12.dp))
                            }

                            items(viewModel.tabYears.size) {
                                Text (
                                    text = viewModel.tabYears[it].toString(),
                                    textDecoration = if (viewModel.selectedYear.intValue == viewModel.tabYears[it]) {
                                        TextDecoration.Underline
                                    } else {
                                        TextDecoration.None
                                    },
                                    color = Color.Black,
                                    modifier = Modifier.clickable {
                                        viewModel.selectedYear.intValue = viewModel.tabYears[it]
                                    }
                                )
                            }
                        }

                        if (viewModel.selectedYear.intValue != -1) {
                            LazyColumn (
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 16.dp, vertical = 16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                val filteredBooks = viewModel.books.filter {
                                    it.publishedChapterDate?.year == viewModel.selectedYear.intValue
                                }
                                items(filteredBooks.size) { idx ->
                                    BookCard (
                                        bookData = filteredBooks[idx],
                                        onClick = {
                                            navController.navigate("detail/${filteredBooks[idx].bookId}")
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

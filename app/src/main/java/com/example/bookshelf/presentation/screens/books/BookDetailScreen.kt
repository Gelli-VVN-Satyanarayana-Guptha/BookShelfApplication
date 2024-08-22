package com.example.bookshelf.presentation.screens.books

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn (
    ExperimentalMaterial3Api::class,
    ExperimentalGlideComposeApi::class,
    ExperimentalLayoutApi::class
)
@Composable
fun BookDetailScreen (
    bookId : String,
    navController: NavController
) {
    val viewModel = hiltViewModel<BookDetailViewModel>()
    viewModel.selectBook(bookId)

    val book = viewModel.selectedBook.value

    var showAddTagDialog by remember {
        mutableStateOf(false)
    }

    book?.let { bookData ->
        MaterialTheme {
            Scaffold (
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = bookData.title,
                                textAlign = TextAlign.Center,
                                overflow = TextOverflow.Ellipsis,
                            )
                        },
                        colors = topAppBarColors(containerColor = Color.Cyan),
                        navigationIcon = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                modifier = Modifier.clickable {
                                    navController.navigateUp()
                                }
                            )
                        }
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(
                        shape = FloatingActionButtonDefaults.largeShape,
                        onClick = {
                            showAddTagDialog = true
                        }
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = "Add Tag")
                    }
                },
                floatingActionButtonPosition = FabPosition.End
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box (
                            modifier = Modifier.size(100.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            GlideImage (
                                model = bookData.image,
                                contentDescription = "Book Thumbnail"
                            )
                        }

                        Row (
                            modifier = Modifier.padding(start = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column (
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text (
                                    text = bookData.title,
                                    color = Color.Black,
                                    fontSize = 18.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Text (
                                    text = "score: ${bookData.score}",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Light,
                                    fontSize = 14.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Text (
                                    text = "Published in ${bookData.publishedChapterDate?.year}",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Thin,
                                    fontSize = 12.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }

                            Icon (
                                imageVector = if (bookData.isFavorite) Icons.Filled.Favorite else Icons.Default.Favorite,
                                contentDescription = "Favorite",
                                modifier = Modifier
                                    .size(30.dp)
                                    .clickable {
                                        viewModel.toggleFavorite()
                                    }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(horizontal = 10.dp)
                            .background(color = Color.LightGray),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(text = bookData.description)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    FlowRow (
                        modifier = Modifier.padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        bookData.tags.forEach { tag ->
                            TagItem(tag = tag)
                        }
                    }
                }
                if (showAddTagDialog) {
                    AddTagDialog(
                        onDismiss = {
                            showAddTagDialog = false
                        },
                        addTag = { tag ->
                            if (tag.isNotEmpty()) {
                                viewModel.addTagToBook(tag)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TagItem(
    tag: String
) {
    Box (
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp),
            text = tag, color = Color.Black
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTagDialog(
    onDismiss: () -> Unit = {},
    addTag: (String) -> Unit = {}
) {
    BasicAlertDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier
            .background(color = Color.White)
            .clip(RoundedCornerShape(50))
    ) {
        var text by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.padding(30.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Add Tag")

            OutlinedTextField(
                modifier = Modifier.padding(top = 15.dp),
                value = text,
                onValueChange = { text = it },
                label = { Text("Enter Note") }
            )

            Button (
                modifier = Modifier
                    .width(100.dp)
                    .padding(top = 20.dp),
                onClick = {
                    addTag(text)
                    onDismiss()
                },
                shape = RoundedCornerShape(10),
            ) {
                Text(text = "Add")
            }
        }
    }
}

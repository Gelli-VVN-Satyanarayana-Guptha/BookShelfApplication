package com.example.bookshelf.presentation.screens.books.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.bookshelf.domain.model.BookData

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BookCard (
    bookData: BookData,
    onClick : () -> Unit
) {
    Card (
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(
                border = BorderStroke(width = Dp.Hairline, color = Color.LightGray),
                shape = RoundedCornerShape(10)
            )
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box (
                modifier = Modifier.size(100.dp),
                contentAlignment = Alignment.Center
            ) {
                GlideImage (
                    modifier = Modifier.clickable {
                        onClick()
                    },
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
            }
        }
    }
}

package com.podium.technicalchallenge.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.podium.technicalchallenge.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
@Composable
fun CardItem(
    imageUrl: String?,
    title: String,
    description: String,
    tags: List<String>?,
    onCardClick: () -> Unit,
){
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .clickable(onClick = {
                onCardClick()
            }),
        shape = RoundedCornerShape(10.dp),
    ) {

        val painter = if (imageUrl != null) {
            rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = imageUrl).apply(block = fun ImageRequest.Builder.() {
                    crossfade(true)
                    placeholder(R.drawable.ic_movie_placeholder)
                }).build()
            )
        } else {
            painterResource(id = R.drawable.ic_movie_placeholder)
        }

        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painter,
            contentScale = ContentScale.None,
            contentDescription = null
        )

        Column(Modifier.padding(8.dp)) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, fontSize = 18.sp, fontWeight = Bold)

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = description)

            tags?.let{
                Row(
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ){
                    for (tag in it){
                        SuggestionChip(
                            label = { Text(text = tag) },
                            onClick = {},
                        )
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun GreenMovie(){
    CardItem(
        imageUrl = null,
        title = "Android Strikes Back",
        description = "A long time ago in a galaxy far, far away...",
        tags = listOf("AndroidWars", "The Return of Apple")) {
    }
}
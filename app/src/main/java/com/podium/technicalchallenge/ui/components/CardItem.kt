package com.podium.technicalchallenge.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.podium.technicalchallenge.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardItem(
    image: Painter,
    title: String,
    description: String,
    footerText: String = "",
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
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = image,
            contentScale = ContentScale.FillWidth,
            contentDescription = null
        )

        Column(Modifier.padding(8.dp)) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, fontSize = 18.sp, fontWeight = Bold)

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = description)

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = footerText)

            tags?.let{
                Row(
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ){
                    for (tag in it){
                        SuggestionChip(
                            label = {
                                Text(text = tag)
                            },
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
        image = painterResource(id = R.drawable.ic_launcher_background),
        title = "Android Strikes Back",
        description = "A long time ago in a galaxy far, far away...",
        tags = listOf("AndroidWars", "The Return of Apple")) {
    }
}
package com.podium.technicalchallenge.ui.screen.movie

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.podium.technicalchallenge.ui.components.CardItem
import com.podium.technicalchallenge.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetail(
    viewModel: MovieViewModel,
    navController: NavController
) {
    val movie by viewModel.selectedEntity.observeAsState()
    viewModel.loadDetails()

    movie?.let { entity ->
        Scaffold(
            topBar =
            {
                SmallTopAppBar(
                    title = { Text(text = "Details") },
                )
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                ) {
                    Spacer(modifier = Modifier.height(40.dp))

                    CardItem(
                        imageUrl = entity.posterPath,
                        title = entity.title,
                        description = entity.overview,
                        tags = entity.genres
                    ) {
                    }

                    Text(
                        text = "Director: ${entity.director}"
                    )

                    Text(
                        text = "Cast: ${entity.cast}"
                    )
                }
            }
        )
    }


}
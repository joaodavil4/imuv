package com.podium.technicalchallenge.ui.screen.movie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.podium.technicalchallenge.R
import com.podium.technicalchallenge.ui.components.CardItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieList(
    viewModel: MovieViewModel
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(title = { Text(text = "Movies") })
        }
    ) {
        val isRefreshing by viewModel.isRefreshing.observeAsState(false)
        val movies by viewModel.items.observeAsState(listOf())

        viewModel.refresh()

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { viewModel.refresh() }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(movies){ item ->
                    CardItem(
                        imageUrl = item.posterPath,
                        title = item.title,
                        description = item.overview,
                        tags = item.genres
                    ) {
                    }
                }
            }
        }

    }
}
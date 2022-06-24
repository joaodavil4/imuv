package com.podium.technicalchallenge.ui.screen.movie

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
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
    val isRefreshing by viewModel.isRefreshing.observeAsState(false)
    val movies by viewModel.items.observeAsState(listOf())

    val selectedGenre by viewModel.selectedGenre.observeAsState("")
    val genres by viewModel.genres.observeAsState(listOf())
    val top5 by viewModel.top5Filter.observeAsState(false)
    viewModel.refresh()
    viewModel.getGenres()

    Scaffold(
        topBar = { SmallTopAppBar(title = { Text(text = "Movies") }) },
        content = {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {

                Spacer(modifier = Modifier.height(40.dp))

                LazyRow(state = rememberLazyListState(), horizontalArrangement = Arrangement.spacedBy(4.dp)){
                    items(genres){ item ->
                        FilterChip(
                            label = { Text(text = item) },
                            onClick = { viewModel.filterGenre(item)},
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_check),
                                    modifier = Modifier.wrapContentSize(),
                                    contentDescription = null,
                                )
                            },
                            selected = item != selectedGenre,
                        )
                    }
                }


                FilterChip(
                    label = { Text(text = "Top 5") },
                    onClick = { viewModel.filterTop5()},
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_check),
                            modifier = Modifier.wrapContentSize(),
                            contentDescription = null,
                        )
                    },
                    selected = !top5,
                )

                SwipeRefresh(
                    state = rememberSwipeRefreshState(isRefreshing),
                    onRefresh = { viewModel.refresh() }
                ) {


                    LazyColumn(
                        state = rememberLazyListState(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(movies) { item ->
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
    )




}

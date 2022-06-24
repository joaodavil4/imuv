package com.podium.technicalchallenge.ui.screen.movie

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.podium.technicalchallenge.ui.navigation.NavDestinations

@Composable
fun MovieNavigation(
    navController: NavHostController = rememberNavController()
){

    val viewModel = viewModel<MovieViewModel>()
    NavHost(navController = navController, startDestination = NavDestinations.MOVIE_LIST) {
        composable(NavDestinations.MOVIE_LIST) {
            MovieList(viewModel, navController)
        }

        composable(NavDestinations.MOVIE_DETAIL) {
            MovieDetail(viewModel, navController)
        }
    }
}
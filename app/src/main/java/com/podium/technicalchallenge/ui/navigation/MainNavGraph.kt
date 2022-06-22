package com.podium.technicalchallenge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.podium.technicalchallenge.ui.screen.movie.MovieNavigation

@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = NavDestinations.HOME,
    ) {

        composable(route = NavDestinations.HOME) {
            MovieNavigation()
        }
    }
}
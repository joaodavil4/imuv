package com.podium.technicalchallenge.ui.screen.movie

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.podium.technicalchallenge.ui.navigation.NavDestinations

@Composable
fun MovieNavigation(
    navController: NavHostController = rememberNavController()
){
    NavHost(navController = navController, startDestination = NavDestinations.MOVIE_LIST) {
        composable(NavDestinations.MOVIE_LIST) {
            MovieList()
        }
    }
}
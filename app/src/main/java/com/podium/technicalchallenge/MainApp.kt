package com.podium.technicalchallenge

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.podium.technicalchallenge.ui.navigation.MainNavGraph

@Composable
fun MainApp(){
    val navController = rememberNavController()
    MainNavGraph(navController)
}
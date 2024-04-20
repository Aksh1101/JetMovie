package com.example.jetmovie.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetmovie.screens.home.HomeScreen
import com.example.jetmovie.screens.details.DetailScreen


@Composable
fun MovieNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = MoviesScreens.HomeScreen.name){
        composable(MoviesScreens.HomeScreen.name){
            // here we pass where it should lead us
            HomeScreen(navController = navController)
        }
        composable(MoviesScreens.DetailScreen.name + "/{movie}",
            arguments = listOf(navArgument(name = "movie") {type = NavType.StringType})
        ){ backStackEntry ->
            DetailScreen(navController = navController, backStackEntry.arguments?.getString("movie"))
        }
    }
}
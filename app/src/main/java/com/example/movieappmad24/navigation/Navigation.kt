package com.example.movieappmad24.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad24.screens.DetailScreen
import com.example.movieappmad24.screens.HomeScreen
import com.example.movieappmad24.screens.WatchlistScreen
import com.example.movieappmad24.viewModels.MoviesViewModel


@ExperimentalMaterial3Api
@Composable
fun Navigation() {
    val navController = rememberNavController()

    val moviesViewModel: MoviesViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController, moviesViewModel = moviesViewModel)
        }

        composable(
            route = Screen.Detail.route + "/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.StringType })
        ) { backStackEntry ->
            DetailScreen(navController, movieId = backStackEntry.arguments?.getString("movieId"), moviesViewModel = moviesViewModel)
        }

        composable(route = Screen.Watchlist.route) {
            WatchlistScreen(navController, moviesViewModel = moviesViewModel)
        }
    }
}
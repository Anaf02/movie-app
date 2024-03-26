package com.example.movieappmad24

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.navigation.Screen

@ExperimentalMaterial3Api
@Composable
fun WatchlistScreen(navController: NavController) {
    Scaffold(
        topBar = { TopBar(title = "Movie App", false, navController) },
        bottomBar = {
            BottomBar(
                onHomeClicked = { navController.navigate(Screen.Home.route) },
                onWatchlistClicked = { navController.navigate(Screen.Watchlist.route) })
        }
    ) { innerPadding ->
        MovieList(
            modifier = Modifier.padding(innerPadding),
            movies = getMovies(),
            navController = navController
        )
    }
}
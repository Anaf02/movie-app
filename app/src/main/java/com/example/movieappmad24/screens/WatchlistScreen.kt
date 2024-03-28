package com.example.movieappmad24.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.movieappmad24.models.BottomBarItem
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.ui.theme.CustomColorSchemes
import com.example.movieappmad24.widgets.MovieList
import com.example.movieappmad24.widgets.SimpleBottomAppBar
import com.example.movieappmad24.widgets.SimpleTopAppBar

@ExperimentalMaterial3Api
@Composable
fun WatchlistScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            SimpleTopAppBar(
                title = BottomBarItem.bottomBarItems()[1].title,
                true,
                topAppBarColors = CustomColorSchemes.topNavigationColorScheme(),
                navController = navController
            )
        },
        bottomBar = {
            SimpleBottomAppBar(
                navController = navController,
                navigationItems = BottomBarItem.bottomBarItems(),
                navigationBarItemColors = CustomColorSchemes.navigationColorScheme()
            )
        }
    ) { innerPadding ->
        MovieList(
            modifier = Modifier.padding(innerPadding),
            movies = getMovies().asReversed(),
            navController = navController
        )
    }
}
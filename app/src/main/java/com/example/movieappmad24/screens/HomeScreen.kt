package com.example.movieappmad24.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.movieappmad24.data.MovieDatabase
import com.example.movieappmad24.models.BottomBarItem
import com.example.movieappmad24.repositories.MovieRepository
import com.example.movieappmad24.viewModels.HomeMoviesViewModel
import com.example.movieappmad24.viewModels.MoviesViewModelFactory
import com.example.movieappmad24.widgets.MovieList
import com.example.movieappmad24.widgets.SimpleBottomAppBar
import com.example.movieappmad24.widgets.SimpleTopAppBar

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val db = MovieDatabase.getDatabase(LocalContext.current)
    val repository = MovieRepository(movieDao = db.movieDao())
    val factory = MoviesViewModelFactory(repository = repository)
    val viewModel: HomeMoviesViewModel = viewModel(factory = factory)

    val movies by viewModel.movies.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            SimpleTopAppBar(
                title = BottomBarItem.bottomBarItems()[0].title,
                showBackArrow = false,
                navController = navController
            )
        },
        bottomBar = {
            SimpleBottomAppBar(
                navController = navController,
                navigationItems = BottomBarItem.bottomBarItems()
            )
        }
    ) { innerPadding ->
        MovieList(
            modifier = Modifier.padding(innerPadding),
            movies = movies,
            navController = navController,
            toggleFavorite = viewModel::toggleIsFavorite
        )
    }
}
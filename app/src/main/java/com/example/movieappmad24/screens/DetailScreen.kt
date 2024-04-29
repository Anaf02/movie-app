package com.example.movieappmad24.screens

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavController
import com.example.movieappmad24.data.MovieDatabase
import com.example.movieappmad24.repositories.MovieRepository
import com.example.movieappmad24.viewModels.DetailMoviesViewModel
import com.example.movieappmad24.viewModels.MoviesViewModelFactory
import com.example.movieappmad24.widgets.HorizontalScrollableImageView
import com.example.movieappmad24.widgets.MoviePlayer
import com.example.movieappmad24.widgets.MovieRow
import com.example.movieappmad24.widgets.SimpleTopAppBar


@OptIn(UnstableApi::class)
@ExperimentalMaterial3Api
@Composable
fun DetailScreen(
    navController: NavController,
    movieId: String?
) {
    val db = MovieDatabase.getDatabase(LocalContext.current)
    val repository = MovieRepository(movieDao = db.movieDao())
    val factory = MoviesViewModelFactory(repository = repository)
    val viewModel: DetailMoviesViewModel = viewModel(factory = factory)

    movieId?.let {
        val movie = viewModel.getMovieById(movieId)
        Scaffold(
            topBar = {
                SimpleTopAppBar(
                    title = movie.movie.title,
                    showBackArrow = true,
                    navController = navController
                )
            }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier.padding(innerPadding)
            ) {
                item {
                    MovieRow(
                        movieWithImages = movie,
                        onFavClick = { viewModel.toggleIsFavorite(movie.movie.id) }
                    )
                }
                item {
                    MoviePlayer(movieTrailer = movie.movie.trailer)
                }
                item {
                    HorizontalScrollableImageView(movieWithImages = movie)
                }
            }
        }
    }
}
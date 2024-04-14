package com.example.movieappmad24.screens

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavController
import com.example.movieappmad24.viewModels.MoviesViewModel
import com.example.movieappmad24.widgets.HorizontalScrollableImageView
import com.example.movieappmad24.widgets.MoviePlayer
import com.example.movieappmad24.widgets.MovieRow
import com.example.movieappmad24.widgets.SimpleTopAppBar


@OptIn(UnstableApi::class) @ExperimentalMaterial3Api
@Composable
fun DetailScreen(
    navController: NavController,
    movieId: String?,
    moviesViewModel: MoviesViewModel
) {
    movieId?.let {
        val movie = moviesViewModel.movieList.filter { movie -> movie.id == movieId }[0]

        Scaffold(
            topBar = {
                SimpleTopAppBar(
                    title = movie.title,
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
                        movie = movie,
                        onFavClick = { moviesViewModel.toggleIsFavorite(movie.id) }
                    )
                }
                item {
                    MoviePlayer(movieTrailer = movie.trailer)
                }
                item {
                    HorizontalScrollableImageView(movie = movie)
                }
            }
        }
    }
}
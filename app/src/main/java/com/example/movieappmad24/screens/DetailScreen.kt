package com.example.movieappmad24.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad24.models.getMovieById
import com.example.movieappmad24.ui.theme.CustomColorSchemes.topNavigationColorScheme
import com.example.movieappmad24.widgets.HorizontalScrollableImages
import com.example.movieappmad24.widgets.MovieRow
import com.example.movieappmad24.widgets.SimpleTopAppBar

@ExperimentalMaterial3Api
@Composable
fun DetailScreen(navController: NavController, movieId: String?) {
    Scaffold(
        topBar = {
            SimpleTopAppBar(
                title = getMovieById(movieId.toString()).title,
                true,
                topAppBarColors = topNavigationColorScheme(),
                navController = navController
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            MovieRow(
                getMovieById(movieId = movieId.toString())
            )
            HorizontalScrollableImages(movieImages = getMovieById(movieId.toString()).images)
        }
    }
}
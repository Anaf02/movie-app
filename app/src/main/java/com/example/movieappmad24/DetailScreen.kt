package com.example.movieappmad24

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad24.models.getMovieById

@ExperimentalMaterial3Api
@Composable
fun DetailScreen(navController: NavController, movieId: String?) {
    Scaffold(
        topBar = { TopBar(title = getMovieById(movieId.toString()).title, true, navController) }
    ) { innerPadding ->
//        Text(modifier = Modifier.padding(innerPadding), text = "Hello detail screen ${movieId}")
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            MovieRow(
                getMovieById(movieId.toString())
            )
            HorizontalScrollableImages(movieImages = getMovieById(movieId.toString()).images)

        }
    }
}
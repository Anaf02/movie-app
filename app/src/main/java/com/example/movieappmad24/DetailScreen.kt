package com.example.movieappmad24

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad24.models.getMovies

@ExperimentalMaterial3Api
@Composable
fun DetailScreen(navController: NavController, movieId: String?) {
    Scaffold(
        topBar = { TopBar(title = "Movie App") },
        bottomBar = {
            BottomBar { navController.navigate(route = "homescreen") }
        }
    ) { innerPadding ->
        Text(modifier = Modifier.padding(innerPadding),text = "Hello detail screen ${movieId}")
    }
}
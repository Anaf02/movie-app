package com.example.movieappmad24

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.navigation.BottomBarItem

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopBar(title = BottomBarItem.bottomBarItems()[0].title, false, navController) },
        bottomBar = {
            BottomBar(
                navController = navController)
        }
    ) { innerPadding ->
        MovieList(
            modifier = Modifier.padding(innerPadding),
            movies = getMovies(),
            navController = navController
        )
    }
}
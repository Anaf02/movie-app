package com.example.movieappmad24

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.movieappmad24.models.getMovies

@ExperimentalMaterial3Api
@Composable
fun DetailScreen() {
    Scaffold(
        topBar = { TopBar("Movie App") },
        content = { innerPadding ->
            Column(
                Modifier
                    .padding(innerPadding)
            ) {
                Spacer(modifier = Modifier.weight(1f))
            }
        },
        bottomBar = { BottomBar() }
    )
}
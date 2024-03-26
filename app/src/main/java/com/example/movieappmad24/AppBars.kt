package com.example.movieappmad24

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@ExperimentalMaterial3Api
@Composable
fun TopBar(title: String, showBackArrow: Boolean = false, navController: NavController) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        navigationIcon = {
            if (showBackArrow) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.clickable { navController.navigateUp() }
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    onHomeClicked: () -> Unit = {},
    onWatchlistClicked: () -> Unit = {}
) {
    Column(modifier = modifier) {
        NavigationBar {
            NavigationBarItem(
                label = { Text("Home") },
                selected = true,
                onClick = onHomeClicked,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Go to home"
                    )
                }
            )
            NavigationBarItem(
                label = { Text("Watchlist") },
                selected = false,
                onClick = onWatchlistClicked,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Go to watchlist"
                    )
                }
            )
        }
    }
}


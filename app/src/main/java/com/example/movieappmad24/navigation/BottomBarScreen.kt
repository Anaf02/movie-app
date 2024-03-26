package com.example.movieappmad24.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home :
        BottomBarScreen(
            route = Screen.Home.route,
            title = "Home",
            icon = Icons.TwoTone.Home
        )
    object Watchlist :
        BottomBarScreen(
            route = Screen.Watchlist.route,
            title = "Watchlist",
            icon = Icons.TwoTone.Star
        )
}
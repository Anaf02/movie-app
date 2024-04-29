package com.example.movieappmad24.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.movieappmad24.navigation.Screen

data class BottomBarItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    companion object {
        fun bottomBarItems(): List<BottomBarItem> {
            return listOf(
                BottomBarItem(
                    route = Screen.Home.route,
                    title = "Home",
                    icon = Icons.TwoTone.Home,
                ),
                BottomBarItem(
                    route = Screen.Watchlist.route,
                    title = "Watchlist",
                    icon = Icons.TwoTone.Star
                )
            )

        }
    }
}
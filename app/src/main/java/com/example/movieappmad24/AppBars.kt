package com.example.movieappmad24

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.movieappmad24.navigation.BottomBarItem

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
fun BottomBar(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = Color.White
    ) {
        BottomBarItem.bottomBarItems().forEach { navItem ->
            val isSelected = currentDestination?.route == navItem.route

            NavigationBarItem(
                selected = isSelected,
                label = {
                    Text(text = navItem.title)
                },
                onClick = {
                    if (!isSelected) {
                        navController.navigate(navItem.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(imageVector = navItem.icon, contentDescription = "Navigation Icon")
                }
            )
        }

    }
}
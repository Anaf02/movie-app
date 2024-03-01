package com.example.movieappmad24

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun TopBar(title: String) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Modifier.padding(top = 110.dp)
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        navigationIcon = { TopBarIcon(Icons.Default.Menu, "Menu") },
        actions = { TopBarIcon(Icons.Default.Edit, "Edit") },
        scrollBehavior = scrollBehavior,
    )
}

@Composable
fun TopBarIcon(icon: ImageVector, contentDescription: String) {
    IconButton(onClick = { /*TODO*/ }) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription
        )
    }
}

@Composable
fun BottomBar() {
    BottomAppBar(
        actions = {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                BottomBarButton(Icons.Default.Home, "Home")
                BottomBarButton(Icons.Default.Star, "Watchlist")
            }
        }
    )
}

@Composable
fun BottomBarButton(icon: ImageVector, text: String) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(Color.Gray)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = icon, contentDescription = text)
            Text(text)
        }
    }
}

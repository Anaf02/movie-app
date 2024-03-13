package com.example.movieappmad24.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieappmad24.DetailScreen
import com.example.movieappmad24.HomeScreen


@ExperimentalMaterial3Api
@Composable
fun Navigation(modifier: Modifier){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "homescreen" ){
        composable(route="homescreen"){
            HomeScreen()
        }

        composable("detailscreen"){
            DetailScreen()
        }
    }
}
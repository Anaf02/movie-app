@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.movieappmad24

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }

                Scaffold(
                    topBar = { TopBar("Movie App") },
                    content = { innerPadding ->
                        Column(
                            Modifier
                                .padding(innerPadding)
                        ) {
                            Spacer(modifier = Modifier.weight(1f))
                            MovieList(getMovies())
                        }
                    },
                    bottomBar = { BottomBar() }
                )

            }
        }
    }

    @ExperimentalMaterial3Api
    @Composable
    fun TopBar(title: String) {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        Modifier.padding(top = 110.dp)
        CenterAlignedTopAppBar(
            title = {
                Text(text = title)
            },
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu"
                    )
                }
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit"
                    )
                }
            },
            scrollBehavior = scrollBehavior,
        )
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
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(Color.Gray)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Home"
                            )
                            Text("Home")
                        }
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(Color.Gray)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Watchlist",
                            )
                            Text("Watchlist")
                        }
                    }
                }

            })
    }

//    @Composable
//    fun MovieRow(movie: Movie) {
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(5.dp),
//            shape = ShapeDefaults.Large,
//            elevation = CardDefaults.cardElevation(10.dp)
//        ) {
//            Column {
//                Box(
//                    modifier = Modifier
//                        .height(150.dp)
//                        .fillMaxWidth(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.movie_image),
//                        contentDescription = "placeholder image",
//                        contentScale = ContentScale.Crop
//                    )
//
//                    Box(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(10.dp),
//                        contentAlignment = Alignment.TopEnd
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.FavoriteBorder,
//                            contentDescription = "Add to favorites",
//                            Modifier.background(color = Color.White)
//                        )
//                    }
//                }
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(5.dp),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Text(text = movie.title)
//                    Icon(
//                        imageVector = Icons.Default.KeyboardArrowUp,
//                        contentDescription = "dropdown"
//                    )
//                }
//            }
//        }
//    }

    @Composable
    fun MovieList(movies: List<Movie>) {
        LazyColumn {
            items(movies) { movie ->
                ExpandableCard(movie)
            }
        }
    }
}


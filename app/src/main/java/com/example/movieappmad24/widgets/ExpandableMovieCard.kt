package com.example.movieappmad24.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.navigation.Screen
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme


@Composable
fun MovieList(
    modifier: Modifier,
    movies: List<Movie> = getMovies(),
    navController: NavController
) {
    LazyColumn(modifier = modifier) {
        items(movies) { movie ->
            MovieRow(movie = movie, onItemClick = { movieId ->
//                Log.d("MovieList", "My callback value: $movieId")
                navController.navigate(route = Screen.Detail.withArgs(movieId))
            })
        }
    }
}

@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                onItemClick(movie.id)
            },
        shape = ShapeDefaults.Large,
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column {
            MovieCardHeader(imageUrl = movie.images[0])

            ExpandableMovieDetails(modifier = Modifier.padding(12.dp), movie = movie)
        }
    }
}

@Composable
fun MovieCardHeader(imageUrl: String) {
    Box(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        MovieImage(imageUrl)
        FavoriteIcon()
    }
}

@Composable
fun MovieImage(imageUrl: String) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentScale = ContentScale.Crop,
        contentDescription = "movie poster",
        loading = {
            CircularProgressIndicator()
        }
    )
}

@Composable
fun FavoriteIcon() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Icon(
            tint = MaterialTheme.colorScheme.secondary,
            imageVector = Icons.Default.FavoriteBorder,
            contentDescription = "Add to favorites"
        )
    }
}


@Composable
fun ExpandableMovieDetails(modifier: Modifier, movie: Movie) {
    var showDetails by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = movie.title)
        ExpandOrCollapseArrowIcon(
            showDetails = showDetails,
            onClick = { showDetails = !showDetails })
    }

    AnimatedVisibility(
        visible = showDetails,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        MovieDetails(modifier = modifier, movie = movie)
    }
}

@Composable
fun MovieDetails(modifier: Modifier, movie: Movie) {
    Column(modifier = modifier) {

        MovieDetailRow("Director:", movie.director, Icons.Filled.Person)
        MovieDetailRow("Released:", movie.year, Icons.Filled.Check)
        MovieDetailRow("Genre:", movie.genre, Icons.Filled.DateRange)
        MovieDetailRow("Actors:", movie.actors, Icons.Filled.Face)
        MovieDetailRow("Rating:", movie.rating, Icons.Filled.Star)

        Divider(modifier = Modifier.padding(3.dp))

        MoviePlot(plot = movie.plot)
    }
}

@Composable
fun MovieDetailRow(label: String, value: String, icon: ImageVector) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        SmallDescriptiveIcon(icon, Modifier.padding(end = 4.dp))
        Text(
            text = "$label $value",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun SmallDescriptiveIcon(icon: ImageVector, modifier: Modifier) {
    Icon(
        imageVector = icon,
        contentDescription = "DescriptiveIcon",
        tint = MaterialTheme.colorScheme.onSurface,
        modifier = modifier
    )
}

@Composable
fun MoviePlot(plot: String) {
    Row {
        SmallDescriptiveIcon(icon = Icons.Filled.PlayArrow, Modifier.padding(end = 4.dp))
        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)) {
                append("Plot: ")
            }
            Row {
                withStyle(
                    style = SpanStyle(
                        color = Color.DarkGray,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal
                    )
                ) {
                    append(plot)
                }
            }
        })
    }
}

@Composable
private fun ExpandOrCollapseArrowIcon(showDetails: Boolean, onClick: () -> Unit) {
    val icon = if (showDetails) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    Icon(
        modifier = Modifier.clickable { onClick() },
        imageVector = icon,
        contentDescription = "Expand/Collapse"
    )
}

@Composable
fun HorizontalScrollableImages(movieImages: List<String>) {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            state = rememberLazyListState()
        ) {
            items(movieImages) { imageUrl ->
                Card(
                    modifier = Modifier
                        .height(220.dp)
                        .width(maxWidth)
                        .padding(16.dp)
                ) {
                    MovieImage(imageUrl = imageUrl)
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MovieAppMAD24Theme {
        MovieList(
            modifier = Modifier,
            movies = getMovies(),
            navController = rememberNavController()
        )
    }
}
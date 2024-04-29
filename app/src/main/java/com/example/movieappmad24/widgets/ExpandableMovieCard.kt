package com.example.movieappmad24.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.navigation.Screen

@Composable
fun MovieList(
    modifier: Modifier,
    moviesWithImages: List<MovieWithImages>,
    navController: NavController,
    toggleFavorite: (String) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(moviesWithImages) { movieWithImages ->
            MovieRow(
                modifier = Modifier.padding(5.dp),
                movieWithImages = movieWithImages,
                onMovieRowClick = { movieId ->
                    navController.navigate(route = Screen.Detail.withArgs(movieId))
                },
                onFavClick = { toggleFavorite(movieWithImages.movie.id) }
            )
        }
    }
}

@Composable
fun MovieRow(
    modifier: Modifier = Modifier,
    movieWithImages: MovieWithImages,
    onMovieRowClick: (String) -> Unit = {},
    onFavClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .padding(2.dp)
            .fillMaxWidth()
            .clickable {
                onMovieRowClick(movieWithImages.movie.id)
            },
        shape = ShapeDefaults.Large,
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column {

            MovieCardHeader(
                imageUrl = movieWithImages.images[0].url,
                movieWithImages.movie.isFavorite,
                onFavClick
            )

            ExpandableMovieDetails(
                modifier = Modifier.padding(12.dp),
                movieWithImages = movieWithImages
            )
        }
    }
}

@Composable
fun MovieCardHeader(
    imageUrl: String,
    isFavorite: Boolean = false,
    onFavoriteClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.TopEnd
    ) {

        MovieImage(imageUrl)

        FavoriteIcon(
            isFavorite = isFavorite,
            onToggleFavorite = onFavoriteClick
        )
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
fun FavoriteIcon(
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .padding(10.dp)
            .clickable { onToggleFavorite() },
        contentAlignment = Alignment.TopEnd
    ) {
        Icon(
            tint = if (isFavorite) Color.Red else MaterialTheme.colorScheme.secondary,
            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "Add to favorites"
        )
    }
}


@Composable
fun ExpandableMovieDetails(modifier: Modifier, movieWithImages: MovieWithImages) {
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
        Text(text = movieWithImages.movie.title)
        ExpandOrCollapseArrowIcon(
            showDetails = showDetails,
            onClick = { showDetails = !showDetails })
    }

    AnimatedVisibility(
        visible = showDetails,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        MovieDetails(modifier = modifier, movieWithImages = movieWithImages)
    }
}

@Composable
fun MovieDetails(modifier: Modifier, movieWithImages: MovieWithImages) {
    Column(modifier = modifier) {

        MovieDetailRow("Director:", movieWithImages.movie.director, Icons.Filled.Person)
        MovieDetailRow("Released:", movieWithImages.movie.year, Icons.Filled.Check)
        MovieDetailRow("Genre:", movieWithImages.movie.genre, Icons.Filled.DateRange)
        MovieDetailRow("Actors:", movieWithImages.movie.actors, Icons.Filled.Face)
        MovieDetailRow("Rating:", movieWithImages.movie.rating, Icons.Filled.Star)

        Divider(modifier = Modifier.padding(3.dp))

        MoviePlot(plot = movieWithImages.movie.plot)
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
fun HorizontalScrollableImageView(movieWithImages: MovieWithImages) {
    LazyRow {
        items(movieWithImages.images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Movie poster",
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

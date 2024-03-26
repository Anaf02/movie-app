package com.example.movieappmad24

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
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme


@Composable
fun MovieList(modifier: Modifier, movies: List<Movie> = getMovies(), navController: NavController) {
    LazyColumn(modifier = modifier) {
        items(movies) { movie ->
            MovieRow(movie = movie, onItemClick = { movieId ->
//                Log.d("MovieList", "My callback value: $movieId")
                navController.navigate(route = "detailscreen/$movieId")
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


            MovieDetails(modifier = Modifier.padding(12.dp), movie = movie)

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
fun MovieDetails(modifier: Modifier, movie: Movie) {
    var showDetails by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = movie.title)
        Icon(
            modifier = Modifier
                .clickable {
                    showDetails = !showDetails
                },
            imageVector =
            if (showDetails) Icons.Filled.KeyboardArrowDown
            else Icons.Default.KeyboardArrowUp, contentDescription = "show more"
        )
    }


    AnimatedVisibility(
        visible = showDetails,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column(modifier = modifier) {
            Text(
                text = "Director: ${movie.director}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(text = "Released: ${movie.year}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Genre: ${movie.genre}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.bodySmall)

            Divider(modifier = Modifier.padding(3.dp))

            Text(buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)) {
                    append("Plot: ")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.DarkGray,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal
                    )
                ) {
                    append(movie.plot)
                }
            })
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
                        .height(250.dp)
                        .width(maxWidth)
                        .padding(16.dp)
                ) {
                    MovieImage(imageUrl)
                }
            }
        }

    }
}


//@ExperimentalMaterial3Api
//@Composable
//fun ExpandableCard(movie: Movie, onItemClick: (String) -> Unit = {}) {
//    var expandedState by remember {
//        mutableStateOf(false)
//    }
//    val rotationState by animateFloatAsState(
//        targetValue = if (expandedState) 180f else 0f
//    )
//    Log.d("Movie List:", "Mycallback function:${movie.id}")
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable {
//                onItemClick(movie.id)
//            }
//            .padding(5.dp)
//            .animateContentSize(
//                animationSpec = tween(
//                    durationMillis = 300,
//                    easing = LinearOutSlowInEasing
//                )
//            ),
//        shape = ShapeDefaults.Large,
//        elevation = CardDefaults.cardElevation(10.dp)
//    ) {
//        Column {
//            MovieImage(movie.images.first())
//            MovieHeader(movie, rotationState, expandedState) { expandedState = !expandedState }
//            if (expandedState) {
//                MovieDetails(movie)
//            }
//        }
//    }
//}
//
//@ExperimentalMaterial3Api
//@Composable
//fun MovieImage(imageUrl: String) {
//    Box(
//        modifier = Modifier
//            .height(150.dp)
//            .fillMaxWidth(),
//        contentAlignment = Alignment.Center
//    ) {
//        SubcomposeAsyncImage(
//            model = ImageRequest.Builder(LocalContext.current)
//                .data(imageUrl)
//                .crossfade(true)
//                .build(),
//            loading = { CircularProgressIndicator() },
//            contentDescription = "image",
//            contentScale = ContentScale.Crop
//        )
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(10.dp),
//            contentAlignment = Alignment.TopEnd
//        ) {
//            Icon(
//                imageVector = Icons.Filled.FavoriteBorder,
//                contentDescription = "Add to favorites",
//                tint = Color.Cyan
//            )
//        }
//    }
//}
//
//@ExperimentalMaterial3Api
//@Composable
//fun MovieHeader(movie: Movie, rotationState: Float, expandedState: Boolean, onClick: () -> Unit) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(6.dp),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(
//            text = movie.title,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.weight(6f),
//            maxLines = 1,
//            overflow = TextOverflow.Ellipsis
//        )
//        IconButton(onClick = onClick) {
//            Icon(
//                imageVector = Icons.Default.KeyboardArrowDown,
//                contentDescription = "Arrow",
//                modifier = Modifier
//                    .weight(1f)
//                    .rotate(rotationState),
//                onClick = { expandedState = !expandedState }
//            )
//        }
//    }
//}
//
//@ExperimentalMaterial3Api
//@Composable
//@Preview
//fun ExpandableCardPreview() {
//    ExpandableCard(movie = getMovies().elementAt(1))
//}
//
//@Composable
//fun MovieDetails(movie: Movie) {
//    Column(modifier = Modifier.padding(5.dp)) {
//        Text(text = "Director: ${movie.director}")
//        Text(text = "Released: ${movie.year}")
//        Text(text = "Genre: ${movie.genre}")
//        Text(text = "Actors: ${movie.actors}")
//        Text(text = "Rating: ${movie.rating}")
//        Divider(color = Color.Black, thickness = 1.dp)
//        Text(text = "Plot: ${movie.plot}")
//    }
//}
//
//@ExperimentalMaterial3Api
//@Composable
//fun MovieList(movies: List<Movie>) {
//    LazyColumn {
//        items(movies) { movie ->
//            ExpandableCard(movie)
//        }
//    }
//}
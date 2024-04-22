package com.example.movieappmad24.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.repositories.MovieRepository

class MoviesViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movieList = getMovies().toMutableStateList()

    private val _favoriteMoviesList = mutableStateListOf<Movie>()

    val movieList: List<Movie>
        get() = _movieList

    val favoriteList: List<Movie>
        get() = _movieList.filter { it.isFavorite }

    fun toggleIsFavorite(movieId: String) = movieList.find { it.id == movieId }?.let { movie ->
        Log.i("MoviesViewModel", "toggleFavoriteMovie")
        movie.isFavorite = !movie.isFavorite
    }
}
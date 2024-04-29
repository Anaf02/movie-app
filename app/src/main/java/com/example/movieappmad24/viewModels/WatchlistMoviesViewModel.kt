package com.example.movieappmad24.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class WatchlistMoviesViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _favoriteMovies = MutableStateFlow(listOf<Movie>())

    init {
        viewModelScope.launch {
            repository.getAllFavoriteMovies()
                .distinctUntilChanged()
                .collect { listOfFavoriteMovies ->
                    _favoriteMovies.value = listOfFavoriteMovies
                }
        }
    }

    fun toggleIsFavorite(movieId: String) {
        val movie = repository.getMovieById(movieId)

        movie.isFavorite = !movie.isFavorite

        viewModelScope.launch {
            repository.updateMovie(movie)
        }
    }

    val favoriteMovies: StateFlow<List<Movie>> = _favoriteMovies.asStateFlow()
}
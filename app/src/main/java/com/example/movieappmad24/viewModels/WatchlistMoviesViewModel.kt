package com.example.movieappmad24.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class WatchlistMoviesViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _favoriteMovies = MutableStateFlow(listOf<MovieWithImages>())

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
        val movieWithImages = repository.getMovieById(movieId)

        movieWithImages.movie.isFavorite = !movieWithImages.movie.isFavorite

        viewModelScope.launch {
            repository.updateMovie(movieWithImages.movie)
        }
    }

    val favoriteMovies: StateFlow<List<MovieWithImages>> = _favoriteMovies.asStateFlow()
}
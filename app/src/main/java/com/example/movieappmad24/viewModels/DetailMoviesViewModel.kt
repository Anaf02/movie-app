package com.example.movieappmad24.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailMoviesViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _movie = MutableStateFlow<Movie?>(null)
    val movie: Flow<Movie?> = _movie.asStateFlow()

    fun getMovieById(id: String): MovieWithImages {
        var movieWithImages: MovieWithImages? = null

        viewModelScope.launch {
            movieWithImages = repository.getMovieById(id)
        }
        return movieWithImages ?: throw IllegalArgumentException("Movie not found")
    }

    fun toggleIsFavorite(movieId: String) {
        WatchlistMoviesViewModel(repository = repository).toggleIsFavorite(movieId = movieId)
    }
}
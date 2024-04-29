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

class HomeMoviesViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies = MutableStateFlow(listOf<Movie>())

    init {
        viewModelScope.launch {
            repository.getAllMovies()
                .distinctUntilChanged()
                .collect { listOfMovies ->
                    _movies.value = listOfMovies
                }
        }
    }

    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    fun toggleIsFavorite(movieId: String){
        WatchlistMoviesViewModel(repository = repository).toggleIsFavorite(movieId = movieId)
    }
}
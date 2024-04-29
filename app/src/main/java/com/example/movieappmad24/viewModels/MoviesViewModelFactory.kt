package com.example.movieappmad24.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappmad24.repositories.MovieRepository

class MoviesViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {

    private val creators: Map<Class<out ViewModel>, () -> ViewModel> = mapOf(
        HomeMoviesViewModel::class.java to { HomeMoviesViewModel(repository) },
        DetailMoviesViewModel::class.java to { DetailMoviesViewModel(repository) },
        WatchlistMoviesViewModel::class.java to { WatchlistMoviesViewModel(repository) }
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass]
            ?: creators.entries.firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
            ?: throw IllegalArgumentException("Unknown ViewModel class: $modelClass")

        @Suppress("UNCHECKED_CAST")
        return creator.invoke() as T
    }
}
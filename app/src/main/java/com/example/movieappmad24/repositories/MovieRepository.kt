package com.example.movieappmad24.repositories

import com.example.movieappmad24.data.MovieDao;
import com.example.movieappmad24.models.Movie;

class MovieRepository(private val movieDao: MovieDao) {
    suspend fun add(movie: Movie) = movieDao.addMovie(movie)

    suspend fun delete(movie: Movie) = movieDao.deleteMovie(movie)

    suspend fun update(movie: Movie) = movieDao.updateMovie(movie)

    fun getAllMovies() = movieDao.getAllMovies()

    fun getAllFavoriteMovies() = movieDao.getAllFavorite()

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: MovieRepository? = null

        fun getInstance(dao: MovieDao) =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(dao).also { instance = it }
            }
    }
}

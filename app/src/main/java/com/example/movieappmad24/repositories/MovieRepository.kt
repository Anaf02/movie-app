package com.example.movieappmad24.repositories

import com.example.movieappmad24.data.MovieDao;
import com.example.movieappmad24.models.Movie;

class MovieRepository(private val movieDao: MovieDao) {
    suspend fun add(movie: Movie) = movieDao.add(movie)

    suspend fun delete(movie: Movie) = movieDao.delete(movie)

    suspend fun update(movie: Movie) = movieDao.update(movie)

    fun getAllMovies() = movieDao.getAll()

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

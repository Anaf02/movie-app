package com.example.movieappmad24.repositories

import com.example.movieappmad24.data.MovieDao
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieImage
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val movieDao: MovieDao) {
    suspend fun addMovie(movie: Movie) = movieDao.addMovie(movie)

    suspend fun deleteMovie(movie: Movie) = movieDao.deleteMovie(movie)

    suspend fun updateMovie(movie: Movie) = movieDao.updateMovie(movie)

    suspend fun addMovieImages(movieImages: List<MovieImage>) = movieDao.addMovieImages(movieImages)

    fun getAllMovies() = movieDao.getAllMovies()

    fun getAllFavoriteMovies() = movieDao.getAllFavorite()

    fun getMovieById(id: String) = movieDao.getMovieById(id)

    fun getById(id: Long): Flow<Movie?> = movieDao.get(id)

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: MovieRepository? = null

        fun getInstance(dao: MovieDao) =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(dao).also { instance = it }
            }
    }
}

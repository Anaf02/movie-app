package com.example.movieappmad24.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieImage
import com.example.movieappmad24.models.MovieWithImages
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert
    suspend fun addMovie(movie: Movie)

    @Update
    suspend fun updateMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Insert
    suspend fun addMovieImages(movieImages: List<MovieImage>)

    @Query("SELECT * from movies where dbId=:id")
    fun get(id: Long): Flow<Movie>

    @Transaction
    @Query("SELECT * from movies")
    fun getAllMovies(): Flow<List<MovieWithImages>>

    @Transaction
    @Query("SELECT * from movies where id=:movieId")
    fun getMovieById(movieId: String): MovieWithImages

    @Transaction
    @Query("SELECT * from movies where isFavorite = 1")
    fun getAllFavorite(): Flow<List<MovieWithImages>>
}
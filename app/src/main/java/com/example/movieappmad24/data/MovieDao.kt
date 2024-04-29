package com.example.movieappmad24.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.movieappmad24.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert
    suspend fun addMovie(movie: Movie)

    @Update
    suspend fun updateMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query("SELECT * from movies where dbId=:id")
    fun get(id: Long): Flow<Movie>

    @Query("SELECT * from movies")
    fun getAllMovies(): Flow<List<Movie>>

    @Query("SELECT * from movies where id=:movieId")
    fun getMovieById(movieId: String): Movie

    @Query("SELECT * from movies where isFavorite = 1")
    fun getAllFavorite(): Flow<List<Movie>>
}
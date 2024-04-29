package com.example.movieappmad24.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "movieImages",
    foreignKeys = [ForeignKey(
        entity = Movie::class,
        parentColumns = ["id"],
        childColumns = ["movieId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("movieId")]
)
data class MovieImage(
    @PrimaryKey
    var id: String,
    var movieId: String,
    var url: String
)
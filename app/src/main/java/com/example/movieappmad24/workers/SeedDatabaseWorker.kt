package com.example.movieappmad24.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.movieappmad24.data.MovieDatabase

class SeedDatabaseWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {
    override fun doWork(): Result {
        val movieDao = MovieDatabase.getDatabase(applicationContext).movieDao()
        





        return Result.success()
    }
}
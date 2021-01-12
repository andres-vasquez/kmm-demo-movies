package com.example.kmmapp.androidApp.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.github.andresvasquez.topmovies.shared.MoviesSharedI
import org.koin.core.KoinComponent
import org.koin.core.inject

class MoviesRefreshWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params), KoinComponent {

    companion object {
        const val WORK_NAME = "MoviesRefreshWorker"
    }

    override suspend fun doWork(): Result {
        return try {
            val shared: MoviesSharedI by inject()
            shared.refreshData()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}
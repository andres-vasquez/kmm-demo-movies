package com.github.andresvasquez.topmovies.androidApp

import android.app.Application
import android.os.Build
import androidx.work.*
import com.example.kmmapp.androidApp.work.MoviesRefreshWorker
import com.github.andresvasquez.topmovies.androidApp.ui.login.LoginViewModel
import com.github.andresvasquez.topmovies.androidApp.ui.moviedetail.MovieDetailViewModel
import com.github.andresvasquez.topmovies.androidApp.ui.movielist.MovieListViewModel
import com.github.andresvasquez.topmovies.androidApp.ui.splash.SplashViewModel
import com.github.andresvasquez.topmovies.shared.MoviesShared
import com.github.andresvasquez.topmovies.shared.MoviesSharedI
import com.github.andresvasquez.topmovies.shared.data.source.local.DatabaseDriverFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MoviesApp : Application() {
    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()

        initDependencyInjection()
        Timber.plant(Timber.DebugTree())
        updateMovies()
    }

    private fun updateMovies() {
        applicationScope.launch {
            val constrains = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresBatteryNotLow(true)
                .setRequiresCharging(true)
                .apply {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        setRequiresDeviceIdle(true)
                    }
                }
                .build()


            val repeatingRequest = PeriodicWorkRequestBuilder<MoviesRefreshWorker>(
                1,
                TimeUnit.DAYS
            )
                .setConstraints(constrains)
                .build()

            WorkManager.getInstance().enqueueUniquePeriodicWork(
                MoviesRefreshWorker.WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                repeatingRequest
            )
        }
    }

    fun initDependencyInjection() {
        /**
         * use Koin Library as a service locator
         */
        val myModule = module {
            //Declare a ViewModel - be later inject into Fragment with dedicated injector using by viewModel()
            viewModel { SplashViewModel(get(), get()) }
            viewModel { LoginViewModel(get(), get()) }
            viewModel { MovieListViewModel(get(), get()) }
            viewModel { MovieDetailViewModel(get(), get()) }
            single {
                MoviesShared(DatabaseDriverFactory(this@MoviesApp)) as MoviesSharedI
            }
        }

        startKoin {
            androidContext(this@MoviesApp)
            modules(listOf(myModule))
        }
    }
}
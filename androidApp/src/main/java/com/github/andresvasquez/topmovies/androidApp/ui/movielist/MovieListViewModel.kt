package com.github.andresvasquez.topmovies.androidApp.ui.movielist

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kmmapp.androidApp.ui.base.BaseViewModel
import com.github.andresvasquez.topmovies.shared.MoviesSharedI
import com.github.andresvasquez.topmovies.shared.data.source.PopularMovie
import com.github.andresvasquez.topmovies.shared.data.source.remote.ApiStatus

class MovieListViewModel(val app: Application, private val shared: MoviesSharedI) :
    BaseViewModel(app, shared) {

    val movies: LiveData<List<PopularMovie>> = MutableLiveData()
    val status: LiveData<ApiStatus> = MutableLiveData()

    init {
        loadMovies()
    }

    private fun loadMovies() {

    }
}
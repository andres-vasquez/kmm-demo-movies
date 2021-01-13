package com.github.andresvasquez.topmovies.androidApp.ui.movielist

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kmmapp.androidApp.ui.base.BaseViewModel
import com.github.andresvasquez.topmovies.shared.MoviesSharedI
import com.github.andresvasquez.topmovies.shared.data.source.PopularMovie
import com.github.andresvasquez.topmovies.shared.data.source.remote.ApiStatus
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MovieListViewModel(val app: Application, private val shared: MoviesSharedI) :
    BaseViewModel(app, shared) {
    private val mainScope = MainScope()

    private val _movies: MutableLiveData<List<PopularMovie>> = MutableLiveData()
    val movies: LiveData<List<PopularMovie>> get() = _movies

    private val _status: MutableLiveData<ApiStatus> = MutableLiveData()
    val status: LiveData<ApiStatus> get() = _status

    init {
        loadMovies()
    }

    private fun loadMovies() {
        _status.postValue(ApiStatus.LOADING)
        mainScope.launch {
            kotlin.runCatching {
                shared.getPopularMovies()
            }.onSuccess {
                _status.postValue(ApiStatus.DONE)
                _movies.postValue(it)
            }.onFailure {
                _status.postValue(ApiStatus.ERROR)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        mainScope.cancel()
    }
}
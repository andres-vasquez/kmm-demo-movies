package com.github.andresvasquez.topmovies.androidApp.ui.moviedetail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kmmapp.androidApp.ui.base.BaseViewModel
import com.example.kmmapp.androidApp.ui.base.NavigationCommand
import com.github.andresvasquez.topmovies.shared.MoviesSharedI
import com.github.andresvasquez.topmovies.shared.data.source.PopularMovie
import com.github.andresvasquez.topmovies.shared.data.source.remote.model.Genre
import kotlinx.coroutines.launch

class MovieDetailViewModel(val app: Application, private val shared: MoviesSharedI) :
    BaseViewModel(app, shared) {

    private val _genres = MutableLiveData<List<Genre>>()
    val genres: LiveData<List<Genre>> get() = _genres

    fun loadGenres(movie: PopularMovie) {
        if (!movie.genre_ids.isNullOrEmpty()) {
            val set: Set<Int> = movie.genre_ids.toHashSet()
            viewModelScope.launch {
                _genres.postValue(shared.getGenres(set))
            }
        }
    }

    fun navigateToBack() {
        navigationCommand.value = NavigationCommand.Back
    }
}
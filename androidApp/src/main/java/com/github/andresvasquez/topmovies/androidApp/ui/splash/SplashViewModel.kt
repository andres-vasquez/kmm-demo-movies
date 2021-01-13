package com.github.andresvasquez.topmovies.androidApp.ui.splash

import android.app.Application
import com.example.kmmapp.androidApp.ui.base.BaseViewModel
import com.example.kmmapp.androidApp.ui.base.NavigationCommand
import com.github.andresvasquez.topmovies.androidApp.utils.asParcelable
import com.github.andresvasquez.topmovies.shared.MoviesSharedI

class SplashViewModel(val app: Application, private val shared: MoviesSharedI) :
    BaseViewModel(app, shared) {

    fun navigateToTheNextScreen() {
        if (shared.getUserPrefs() != null) {
            navigationCommand.value =
                NavigationCommand.To(
                    SplashFragmentDirections.actionSplashFragmentToMovieListFragment(
                        shared.getUserPrefs()!!.asParcelable()
                    )
                )
        } else {
            navigationCommand.value =
                NavigationCommand.To(
                    SplashFragmentDirections.actionSplashFragmentToLoginFragment()
                )
        }
    }
}
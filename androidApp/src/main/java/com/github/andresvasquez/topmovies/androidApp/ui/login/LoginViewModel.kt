package com.github.andresvasquez.topmovies.androidApp.ui.login

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kmmapp.androidApp.ui.base.BaseViewModel
import com.example.kmmapp.androidApp.ui.base.NavigationCommand
import com.github.andresvasquez.topmovies.androidApp.R
import com.github.andresvasquez.topmovies.androidApp.utils.asParcelable
import com.github.andresvasquez.topmovies.androidApp.utils.validateUser
import com.github.andresvasquez.topmovies.shared.MoviesSharedI
import com.github.andresvasquez.topmovies.shared.data.source.User

class LoginViewModel(val app: Application, private val shared: MoviesSharedI) :
    BaseViewModel(app, shared) {

    private val _editPhoto = MutableLiveData<Boolean>()
    val editPhoto: LiveData<Boolean> get() = _editPhoto

    private val _currentUser = MutableLiveData<User>()
    val currentUser: LiveData<User> get() = _currentUser

    init {
        _editPhoto.value = false
        loadUser()
    }

    private fun loadUser() {
        val user = shared.getUserPrefs()
        if (user != null) {
            _currentUser.value = user!!
        } else {
            _currentUser.value = User("", null, null)
        }
    }

    fun editPhoto() {
        _editPhoto.value = true
    }

    fun doneEditingPhoto() {
        _editPhoto.value = false
    }

    fun validateAndSave(user: User) {
        if (validateUser(user)) {
            shared.saveUserPrefs(user)
            navigationCommand.value = NavigationCommand.To(
                LoginFragmentDirections.actionLoginFragmentToMovieListFragment(user.asParcelable())
            )
        } else {
            showSnackBarInt.value = R.string.error_login_display_name
        }
    }
}
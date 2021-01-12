package com.github.andresvasquez.topmovies.androidApp.utils

import com.github.andresvasquez.topmovies.shared.data.source.User

fun validateUser(user: User): Boolean {
    return !user.displayName.isNullOrEmpty()
}
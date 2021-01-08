package com.github.andresvasquez.topmovies.shared.data.source.prefs

import com.github.andresvasquez.topmovies.shared.data.source.User

interface PrefsDataSourceI {
    fun getUserPrefs(): User?
    fun saveUserPrefs(user: User)
    fun getLanguage(): String
}
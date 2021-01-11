package com.github.andresvasquez.topmovies.shared.data.source.prefs

import com.github.andresvasquez.topmovies.shared.data.source.User
import com.github.andresvasquez.topmovies.shared.utils.Constants
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

class PrefsDataSource : PrefsDataSourceI {
    override fun getUserPrefs(): User? {
        val settings = Settings()
        return settings.get(Constants.SHARED_PREFS_KEY)
    }

    override fun saveUserPrefs(user: User) {
        val settings = Settings()
        settings.set(Constants.SHARED_PREFS_KEY, user)
    }

    override fun getLanguage(): String {
        val settings = Settings()
        val user = settings.get<User>(Constants.SHARED_PREFS_KEY)
        return if (user != null) {
            user.language!!
        } else {
            Constants.DEFAULT_LANGUAGE
        }
    }
}
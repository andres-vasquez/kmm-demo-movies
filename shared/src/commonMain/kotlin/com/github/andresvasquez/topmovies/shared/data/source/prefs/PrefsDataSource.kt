package com.github.andresvasquez.topmovies.shared.data.source.prefs

import com.github.andresvasquez.topmovies.shared.data.source.User
import com.github.andresvasquez.topmovies.shared.utils.Constants
import com.github.andresvasquez.topmovies.shared.utils.convertStringToUser
import com.github.andresvasquez.topmovies.shared.utils.convertUserToString
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

class PrefsDataSource : PrefsDataSourceI {
    override fun getUserPrefs(): User? {
        val settings = Settings()
        return convertStringToUser(settings.getString(Constants.SHARED_PREFS_KEY, ""))
    }

    override fun saveUserPrefs(user: User) {
        val settings = Settings()
        settings.putString(Constants.SHARED_PREFS_KEY, convertUserToString(user))
    }

    override fun getLanguage(): String {
        val user = getUserPrefs()
        return if (user != null) {
            user.language!!
        } else {
            Constants.DEFAULT_LANGUAGE
        }
    }
}
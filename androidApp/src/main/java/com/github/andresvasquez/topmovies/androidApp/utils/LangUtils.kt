package com.github.andresvasquez.topmovies.androidApp.utils

import android.app.Activity
import android.content.res.Configuration
import android.content.res.Resources
import java.util.*

fun setLocale(activity: Activity, languageCode: String?, recreate: Boolean = false) {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)
    val resources: Resources = activity.resources
    val config: Configuration = resources.configuration
    config.setLocale(locale)
    resources.updateConfiguration(config, resources.getDisplayMetrics())
    if (recreate) {
        activity.recreate()
    }
}
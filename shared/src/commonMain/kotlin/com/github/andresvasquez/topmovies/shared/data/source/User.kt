package com.github.andresvasquez.topmovies.shared.data.source

import kotlinx.serialization.Serializable

@Serializable
data class User(
    var displayName: String,
    var photo: String?,
    var language: String?,
)
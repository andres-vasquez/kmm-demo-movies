package com.github.andresvasquez.topmovies.shared.data.source.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class GenresResponse(
    val genres: List<Genre>
)

@Serializable
data class Genre(val id: Int, val name: String)
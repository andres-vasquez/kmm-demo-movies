package com.github.andresvasquez.topmovies.shared.data.source.remote.model

data class GenresResponse(
    val genres: List<Genre>
)

data class Genre(val id: Int, val name: String)
package com.github.andresvasquez.topmovies.shared.data.source.remote.model

import com.github.andresvasquez.topmovies.shared.data.source.local.MovieDTO

data class MoviesResponse(
    val page: Int,
    val results: List<MovieDTO>?,
    val total_pages: Int,
    val total_results: Int,
)
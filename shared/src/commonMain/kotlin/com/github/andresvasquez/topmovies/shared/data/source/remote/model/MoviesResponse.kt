package com.github.andresvasquez.topmovies.shared.data.source.remote.model

import com.github.andresvasquez.topmovies.shared.data.source.PopularMovie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    @SerialName("page")
    val page: Int,

    @SerialName("results")
    val results: List<PopularMovie>?,

    @SerialName("total_pages")
    val total_pages: Int,

    @SerialName("total_results")
    val total_results: Int,
)
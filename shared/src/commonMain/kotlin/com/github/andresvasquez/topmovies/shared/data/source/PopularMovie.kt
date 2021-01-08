package com.github.andresvasquez.topmovies.shared.data.source

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PopularMovie(
    val id: Long,
    @SerialName("original_title") val displayName: String,
    @SerialName("original_language") val language: String,
    val overview: String,
    val adult: Boolean,
    val popularity: Double,
    @SerialName("poster_path") val image: String,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("vote_average") val rating: Double,
    @SerialName("genre_ids") val genre_ids: List<Int>
)
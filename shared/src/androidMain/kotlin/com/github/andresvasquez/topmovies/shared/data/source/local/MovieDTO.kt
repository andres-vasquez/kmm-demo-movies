package com.github.andresvasquez.topmovies.shared.data.source.local

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDTO(
    @SerialName("id")
    val id: Long,
    @SerialName("original_title")
    val originalTitle: String,
    @SerialName("original_language")
    val originalLanguage: String?,
    @SerialName("overview")
    val overview: String?,
    @SerialName("adult")
    val adult: Boolean?,
    @SerialName("popularity")
    val popularity: Double?,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("release_date")
    val releaseDate: Long?,
    @SerialName("rating")
    val rating: Double?,
    @SerialName("genre_ids")
    val genreIds: String?
)
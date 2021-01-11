package com.github.andresvasquez.topmovies.shared.data.source.local

import com.github.andresvasquez.topmovies.shared.data.source.PopularMovie
import com.github.andresvasquez.topmovies.shared.utils.*
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

fun List<MovieDTO>.asDomainModel(): List<PopularMovie> {
    return map {
        PopularMovie(
            id = it.id,
            displayName = it.originalTitle,
            overview = it.overview!!,
            language = it.originalLanguage!!,
            adult = it.adult!!,
            image = it.posterPath!!,
            popularity = it.popularity!!,
            releaseDate = convertLongToTime(it.releaseDate!!, Constants.DATE_FORMAT_DISPLAY),
            rating = it.rating!!,
            genre_ids = convertStringToList(it.genreIds!!)
        )
    }
}

fun List<PopularMovie>.asDatabaseModel(): Array<MovieDTO> {
    return map {
        MovieDTO(
            id = it.id,
            originalTitle = it.displayName,
            originalLanguage = it.language,
            overview = it.overview,
            adult = it.adult,
            popularity = it.popularity,
            posterPath = it.image,
            releaseDate = convertDateToLong(it.releaseDate),
            rating = it.rating,
            genreIds = convertListToString(it.genre_ids)
        )
    }.toTypedArray()
}
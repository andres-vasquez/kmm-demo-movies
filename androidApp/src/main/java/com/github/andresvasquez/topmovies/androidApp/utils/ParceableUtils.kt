package com.github.andresvasquez.topmovies.androidApp.utils

import android.os.Parcelable
import com.github.andresvasquez.topmovies.shared.data.source.PopularMovie
import com.github.andresvasquez.topmovies.shared.data.source.User
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ParcelableUser(
    var displayName: String,
    var photo: String?,
    var language: String?,
) : Parcelable

fun ParcelableUser.asOriginal(): User {
    return let {
        User(
            displayName = it.displayName,
            photo = it.photo,
            language = it.language
        )
    }
}

fun User.asParcelable(): ParcelableUser {
    return let {
        ParcelableUser(
            displayName = it.displayName,
            photo = it.photo,
            language = it.language
        )
    }
}

@Parcelize
data class ParcelablePopularMovie(
    val id: Long,
    val displayName: String,
    val language: String,
    val overview: String,
    val adult: Boolean,
    val popularity: Double,
    val image: String,
    val releaseDate: String,
    val rating: Double,
    val genre_ids: List<Int>
) : Parcelable

fun ParcelablePopularMovie.asOriginal(): PopularMovie {
    return let {
        PopularMovie(
            id = it.id,
            displayName = it.displayName,
            language = it.language,
            overview = it.overview,
            adult = it.adult,
            popularity = it.popularity,
            image = it.image,
            releaseDate = it.releaseDate,
            rating = it.rating,
            genre_ids = it.genre_ids
        )
    }
}

fun PopularMovie.asParcelable(): ParcelablePopularMovie {
    return let {
        ParcelablePopularMovie(
            id = it.id,
            displayName = it.displayName,
            language = it.language,
            overview = it.overview,
            adult = it.adult,
            popularity = it.popularity,
            image = it.image,
            releaseDate = it.releaseDate,
            rating = it.rating,
            genre_ids = it.genre_ids
        )
    }
}
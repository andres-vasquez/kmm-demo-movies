package com.github.andresvasquez.topmovies.shared.data.source.remote

import com.github.andresvasquez.topmovies.shared.data.source.PopularMovie
import com.github.andresvasquez.topmovies.shared.data.source.remote.model.Genre
import com.github.andresvasquez.topmovies.shared.data.Result

interface RemoteDataSourceI {
    suspend fun getPopularMovies(lang: String): Result<List<PopularMovie>>
    suspend fun getGenres(lang: String): Result<List<Genre>>
}

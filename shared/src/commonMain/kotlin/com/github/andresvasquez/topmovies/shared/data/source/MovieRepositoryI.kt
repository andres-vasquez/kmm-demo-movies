package com.github.andresvasquez.topmovies.shared.data.source

import com.github.andresvasquez.topmovies.shared.data.source.remote.model.Genre
import com.github.andresvasquez.topmovies.shared.data.Result

interface MovieRepositoryI {
    fun getUserPrefs(): User?
    fun saveUserPrefs(user: User)
    suspend fun getGenres(genres: Set<Int>): Result<List<Genre>>

    @Throws(Exception::class)
    suspend fun getPopularMovies(): Result<List<PopularMovie>>
}
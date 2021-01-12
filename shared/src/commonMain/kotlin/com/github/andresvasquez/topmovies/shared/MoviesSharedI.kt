package com.github.andresvasquez.topmovies.shared

import com.github.andresvasquez.topmovies.shared.data.source.PopularMovie
import com.github.andresvasquez.topmovies.shared.data.source.User
import com.github.andresvasquez.topmovies.shared.data.source.remote.model.Genre

interface MoviesSharedI {
    @Throws(Exception::class)
    suspend fun getPopularMovies(): List<PopularMovie>

    fun getUserPrefs(): User?
    fun saveUserPrefs(user: User)
    suspend fun getGenres(genres: Set<Int>): List<Genre>
    suspend fun refreshData()
}
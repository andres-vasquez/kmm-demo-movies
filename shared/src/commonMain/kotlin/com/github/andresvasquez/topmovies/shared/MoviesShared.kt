package com.github.andresvasquez.topmovies.shared

import com.github.andresvasquez.topmovies.shared.data.Result
import com.github.andresvasquez.topmovies.shared.data.source.MovieRepository
import com.github.andresvasquez.topmovies.shared.data.source.PopularMovie
import com.github.andresvasquez.topmovies.shared.data.source.User
import com.github.andresvasquez.topmovies.shared.data.source.local.DatabaseDriverFactory
import com.github.andresvasquez.topmovies.shared.data.source.local.LocalDataSource
import com.github.andresvasquez.topmovies.shared.data.source.prefs.PrefsDataSource
import com.github.andresvasquez.topmovies.shared.data.source.remote.RemoteDataSource
import com.github.andresvasquez.topmovies.shared.data.source.remote.model.Genre

class MoviesShared(databaseDriverFactory: DatabaseDriverFactory) : MoviesSharedI {
    private val prefs = PrefsDataSource()
    private val local = LocalDataSource(databaseDriverFactory)
    private val remote = RemoteDataSource()
    private val repository = MovieRepository(prefs, local, remote)

    @Throws(Exception::class)
    override suspend fun getPopularMovies(): List<PopularMovie> {
        return when (val result = repository.getPopularMovies()) {
            is Result.Success -> {
                result.data
            }
            is Result.Error -> {
                throw result.exception
            }
        }
    }

    override fun getUserPrefs(): User? {
        TODO("Not yet implemented")
    }

    override fun saveUserPrefs(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun getGenres(genres: Set<Int>): List<Genre> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshData() {
        TODO("Not yet implemented")
    }
}
package com.github.andresvasquez.topmovies.shared.data.source

import com.github.andresvasquez.topmovies.shared.data.source.local.LocalDataSourceI
import com.github.andresvasquez.topmovies.shared.data.source.prefs.PrefsDataSourceI
import com.github.andresvasquez.topmovies.shared.data.source.remote.RemoteDataSourceI
import com.github.andresvasquez.topmovies.shared.data.source.remote.model.Genre
import com.github.andresvasquez.topmovies.shared.data.Result
import com.github.andresvasquez.topmovies.shared.data.source.local.asDatabaseModel
import com.github.andresvasquez.topmovies.shared.data.source.local.asDomainModel
import com.github.andresvasquez.topmovies.shared.utils.Constants

class MovieRepository(
    private val prefs: PrefsDataSourceI,
    private val local: LocalDataSourceI,
    private val remote: RemoteDataSourceI
) : MovieRepositoryI {

    @Throws(Exception::class)
    override suspend fun getPopularMovies(): Result<List<PopularMovie>> {
        val cachedMovies = local.getMovies()
        val lang = prefs.getLanguage()
        val results = remote.getPopularMovies(lang)
        return when {
            results is Result.Success -> {
                results.also {
                    local.deleteMovies()
                    local.insertMovies(it.data.asDatabaseModel())
                }
            }
            cachedMovies is Result.Success -> {
                Result.Success(cachedMovies.data.asDomainModel())
            }
            else -> {
                results
            }
        }
    }

    override suspend fun getGenres(genres: Set<Int>): List<Genre> {
        TODO("Not yet implemented")
    }

    override fun getUserPrefs(): User? {
        TODO("Not yet implemented")
    }

    override fun saveUserPrefs(user: User) {
        TODO("Not yet implemented")
    }
}
package com.github.andresvasquez.topmovies.shared.data.source.remote

import com.github.andresvasquez.topmovies.shared.data.Result
import com.github.andresvasquez.topmovies.shared.data.source.PopularMovie
import com.github.andresvasquez.topmovies.shared.data.source.remote.model.Genre

class RemoteDataSource : RemoteDataSourceI {
    private val api = MoviesApi()

    override suspend fun getPopularMovies(lang: String): Result<List<PopularMovie>> {
        return try {
            val response = api.getPopularMovies(lang)
            return if (response.results.isNullOrEmpty()) {
                Result.Error(NullPointerException("No movies available"))
            } else {
                Result.Success(response.results)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getGenres(lang: String): Result<List<Genre>> {
        return try {
            val response = api.getGenres(lang)
            return if (!response.genres.isNullOrEmpty()) {
                Result.Success(response.genres)
            } else {
                Result.Success(listOf())
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
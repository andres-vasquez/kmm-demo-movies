package com.github.andresvasquez.topmovies.shared.data.source.local

import com.github.andresvasquez.topmovies.shared.data.Result

interface LocalDataSourceI {
    fun getMovies(): Result<List<MovieDTO>>

    fun getMovieById(movieId: Long): Result<MovieDTO>

    fun insertMovies(movies: Array<MovieDTO>)

    fun deleteMovies()
}
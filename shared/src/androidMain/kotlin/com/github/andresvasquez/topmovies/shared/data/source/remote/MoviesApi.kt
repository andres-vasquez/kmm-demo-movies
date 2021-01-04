package com.github.andresvasquez.topmovies.shared.data.source.remote

import com.github.andresvasquez.topmovies.shared.data.source.remote.model.GenresResponse
import com.github.andresvasquez.topmovies.shared.data.source.remote.model.MoviesResponse
import com.github.andresvasquez.topmovies.shared.utils.Constants
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class MoviesApi {
    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    suspend fun getPopularMovies(): MoviesResponse {
        val path = "${Constants.BASE_URL}/3/movie/popular"
        return httpClient.get(path)
    }

    suspend fun getGenres(): GenresResponse {
        val path = "${Constants.BASE_URL}/3/genre/movie/list"
        return httpClient.get(path)
    }
}
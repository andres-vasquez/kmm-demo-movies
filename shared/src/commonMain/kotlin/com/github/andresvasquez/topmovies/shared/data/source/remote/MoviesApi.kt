package com.github.andresvasquez.topmovies.shared.data.source.remote

import com.github.andresvasquez.topmovies.shared.data.source.remote.model.GenresResponse
import com.github.andresvasquez.topmovies.shared.data.source.remote.model.MoviesResponse
import com.github.andresvasquez.topmovies.shared.utils.Constants
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*

enum class ApiStatus { LOADING, ERROR, DONE }

class MoviesApi {
    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

    suspend fun getPopularMovies(lang: String): MoviesResponse {
        val path2 = "${Constants.BASE_URL}3/movie/popular?lang=${lang}&api_key=${Constants.API_KEY}"
        return httpClient.get(path2)
    }

    suspend fun getGenres(lang: String): GenresResponse {
        val path2 =
            "${Constants.BASE_URL}3/genre/movie/list?lang=${lang}&api_key=${Constants.API_KEY}"
        return httpClient.get(path2)
    }
}
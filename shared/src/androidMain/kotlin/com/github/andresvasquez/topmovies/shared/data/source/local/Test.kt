package com.github.andresvasquez.topmovies.shared.data.source.local

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

class Test {
    private val httpClient = HttpClient(OkHttp) {
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }
}
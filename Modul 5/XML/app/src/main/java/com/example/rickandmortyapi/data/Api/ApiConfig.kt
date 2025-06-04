package com.example.rickandmortyapi.data.Api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object ApiConfig {
    const val baseUrl = "https://rickandmortyapi.com/api/"
    val contentType = "application/json".toMediaType()

    fun getApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory(contentType))
            .build()
            .create(ApiService::class.java)
    }
}
package com.example.rickandmortyapi.data.Api

import CharacterResponse
import retrofit2.http.GET

interface ApiService {
    @GET("character")
    suspend fun getMorty(): CharacterResponse
}
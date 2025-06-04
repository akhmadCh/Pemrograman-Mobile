package com.example.rickandmortyapi.data.repository

import android.content.Context
import com.example.rickandmortyapi.data.Api.ApiConfig
import com.example.rickandmortyapi.data.db.AppDatabase

object CharacterRepositoryInstance {
    fun getRepository(context: Context): CharacterRepository {
        val database = AppDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return CharacterRepository(apiService, database.characterDao())
    }
}
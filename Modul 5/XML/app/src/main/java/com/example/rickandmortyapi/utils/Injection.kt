package com.example.rickandmortyapi.utils

import android.content.Context
import com.example.rickandmortyapi.data.Api.ApiConfig
import com.example.rickandmortyapi.data.db.AppDatabase
import com.example.rickandmortyapi.data.repository.CharacterRepository

object Injection {
    fun provideRepository(context: Context): CharacterRepository {
        val database = AppDatabase.getDatabase(context)
        val dao = database.characterDao()
        val apiService = ApiConfig.getApiService()
        return CharacterRepository(apiService, dao)
    }
}
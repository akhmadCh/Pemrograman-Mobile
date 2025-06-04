package com.example.rickandmortyapi.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapi.data.Api.ApiConfig
import com.example.rickandmortyapi.data.db.AppDatabase
import com.example.rickandmortyapi.data.model.CharacterDto
import com.example.rickandmortyapi.data.model.CharacterEntity
import com.example.rickandmortyapi.data.repository.CharacterRepository
import kotlinx.coroutines.launch

class CharacterViewModel(application: Application): AndroidViewModel(application) {
    private val repository: CharacterRepository

    private val _characters = MutableLiveData<List<CharacterDto>>()
    val characters: LiveData<List<CharacterDto>> get() = _characters
    val favorites: LiveData<List<CharacterEntity>>

    init {
        val db = AppDatabase.getDatabase(application)
        repository = CharacterRepository(ApiConfig.getApiService(), db.characterDao())
        favorites = repository.favorites
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            _characters.value = repository.fetchCharacters()
        }
    }

    fun addFavorite(dto: CharacterDto) {
        viewModelScope.launch {
            repository.addToFavorite(dto)
        }
    }

    fun removeFavorite(entity: CharacterEntity) {
        viewModelScope.launch {
            repository.removeFavorite(entity)
        }
    }
}
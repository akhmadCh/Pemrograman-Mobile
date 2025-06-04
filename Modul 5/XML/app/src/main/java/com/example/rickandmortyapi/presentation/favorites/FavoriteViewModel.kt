package com.example.rickandmortyapi.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapi.data.model.CharacterEntity
import com.example.rickandmortyapi.data.repository.CharacterRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: CharacterRepository): ViewModel() {
    val favorites = repository.favorites

    fun removeFavorite (character: CharacterEntity) {
        viewModelScope.launch {
            repository.removeFavorite(character)
        }
    }
}
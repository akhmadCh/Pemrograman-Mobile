package com.example.rickandmortyapi.data.repository

import androidx.lifecycle.LiveData
import com.example.rickandmortyapi.data.Api.ApiService
import com.example.rickandmortyapi.data.db.CharacterDao
import com.example.rickandmortyapi.data.model.CharacterDto
import com.example.rickandmortyapi.data.model.CharacterEntity

class CharacterRepository(
    private val api: ApiService,
    private val dao: CharacterDao
) {
    val favorites: LiveData<List<CharacterEntity>> = dao.getFavorites()

    suspend fun fetchCharacters(): List<CharacterDto> = api.getMorty().results

    suspend fun addToFavorite(dto: CharacterDto) {
        dao.addToFavorite(CharacterEntity(dto.id, dto.name, dto.species, dto.status, dto.image))
    }

    suspend fun removeFavorite(entity: CharacterEntity) {
        dao.removeFavorite(entity)
    }
}
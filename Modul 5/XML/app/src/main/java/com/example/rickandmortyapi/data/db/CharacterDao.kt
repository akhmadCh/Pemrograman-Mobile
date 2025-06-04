package com.example.rickandmortyapi.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyapi.data.model.CharacterEntity

@Dao
interface CharacterDao {
    @Query("SELECT * FROM favorites")
    fun getFavorites(): LiveData<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorite (character: CharacterEntity)

    @Delete
    suspend fun removeFavorite (character: CharacterEntity)
}
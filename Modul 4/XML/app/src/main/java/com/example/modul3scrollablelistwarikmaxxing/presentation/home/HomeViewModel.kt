package com.example.modul3scrollablelistwarikmaxxing.presentation.home

import android.annotation.SuppressLint
import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modul3scrollablelistwarikmaxxing.R
import com.example.modul3scrollablelistwarikmaxxing.models.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(private val resources: Resources) : ViewModel() {
    // MutableStateFlow untuk menyimpan list item(private)
    private val _characterList = MutableStateFlow<List<Character>>(emptyList())

    // public
    val characterList: StateFlow<List<Character>> get() = _characterList

    // fungsi yang mengembalikan data list character
    @SuppressLint("Recycle")
    private fun getCharacterFlow(): Flow<List<Character>> = flow {
        // mengambil data dari resources
        val dataName = resources.getStringArray(R.array.data_name)
        val dataWarikName = resources.getStringArray(R.array.data_warik_name)
        val dataLinkInstagram = resources.getStringArray(R.array.data_link)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataDetails = resources.getStringArray(R.array.data_details)

        // membuat list
        val listCharacter = ArrayList<Character>()
        for (i in dataName.indices) {
            val character = Character(dataName[i], dataWarikName[i], dataLinkInstagram[i], dataPhoto.getResourceId(i, -1), dataDetails[i])
            listCharacter.add(character)
        }
        // untuk free up memory
        dataPhoto.recycle()
        // mengirim (emit) item ke collector
        emit(listCharacter)
    }

    fun loadCharacters() {
        // jalankan coroutine
        viewModelScope.launch {
            getCharacterFlow()
                .onStart {
                    _characterList.value = emptyList()
                }
                .collect { characters ->
                    // saat data berhasil emit oleh flow, update StateFlow
                    _characterList.value = characters
                }
        }
    }
}
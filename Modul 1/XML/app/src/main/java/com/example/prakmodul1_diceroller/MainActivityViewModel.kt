package com.example.prakmodul1_diceroller

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    val number1 = MutableLiveData(0)
    val number2 = MutableLiveData(0)
    var text: String? = null

    fun rollDice () {
        number1.value = (1..6).random()
        number2.value = (1..6).random()
        text = if (number1.value == number2.value) "Selamat anda dapat dadu double!" else "Anda belum beruntung!"
    }
}
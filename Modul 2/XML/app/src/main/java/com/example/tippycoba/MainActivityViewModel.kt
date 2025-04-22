package com.example.tippycoba

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.ceil

class MainActivityViewModel : ViewModel() {
    private val _tipAmount = MutableLiveData(0.0)
    val tipAmount: LiveData<Double> = _tipAmount

    fun calculateTip (cost: Double, tipPercent: Double, roundTip: Boolean) {
        var tip = cost * tipPercent
        if (roundTip) {
            tip = ceil(tip)
        }
        _tipAmount.value = tip
    }
}
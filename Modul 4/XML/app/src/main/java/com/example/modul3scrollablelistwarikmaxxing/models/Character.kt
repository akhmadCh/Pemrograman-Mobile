package com.example.modul3scrollablelistwarikmaxxing.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character (
    val name: String,
    val warikName: String,
    val linkInstagram: String,
    val photo: Int,
    val details: String
): Parcelable
package com.example.beeceptorapi.models

import com.google.gson.annotations.SerializedName

data class Todos (
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("completed") val completed: Boolean
)
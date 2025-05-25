package com.example.beeceptorapi

import com.google.gson.annotations.SerializedName

data class Response<T>(

	@field:SerializedName("Response")
	val response: List<ResponseItem?>? = null
)

data class ResponseItem (

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("completed")
	val completed: Boolean? = null,

	@field:SerializedName("title")
	val title: String? = null
)

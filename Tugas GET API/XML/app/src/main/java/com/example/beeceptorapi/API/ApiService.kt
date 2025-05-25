package com.example.beeceptorapi.API


import com.example.beeceptorapi.models.Todos
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("todos")
    suspend fun getTodos () : Response<List<Todos>>
}
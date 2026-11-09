package com.grupo.app.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    // Rota do integrante. Cada colega deve usar uma rota diferente
    // (ex.: "posts", "users", "todos", "comments"...).
    @GET("albums")
    suspend fun listarAlbuns(): List<Album>
}

object Api {
    val service: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

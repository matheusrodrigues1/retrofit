package com.grupo.app.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


// Define os endpoints da API para consumo assíncrono via Retrofit, mapeando a rota "albums" para uma lista de objetos Album
interface ApiService {
    @GET("albums")
    suspend fun listarAlbuns(): List<Album>
}


// Singleton responsável por prover de forma preguiçosa (lazy) a instância configurada do Retrofit com conversor Gson para a interface ApiService
object Api {
    val service: ApiService by lazy {
        Retrofit.Builder() //configura o retrofit
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create()) //Configura o Gson como conversor de dados
            .build() //Finaliza a configuração e constrói a instância do Retrofit
            .create(ApiService::class.java) //Cria uma implementação da interface ApiService
    }
}

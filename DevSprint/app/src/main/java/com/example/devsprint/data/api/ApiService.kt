package com.example.devsprint.data.api

import com.example.devsprint.data.model.*
import retrofit2.http.*

interface ApiService {

    @GET("https://evilinsult.com/generate_insult.php?lang=en&type=json")
    suspend fun getInsult(): InsultResponse

    @GET("https://rickandmortyapi.com/api/character/{id}")
    suspend fun getRickMorty(
        @Path("id") id: Int
    ): RickMortyResponse

    @GET("https://api.thecatapi.com/v1/images/search")
    suspend fun getCatImage(): List<CatResponseItem>
}
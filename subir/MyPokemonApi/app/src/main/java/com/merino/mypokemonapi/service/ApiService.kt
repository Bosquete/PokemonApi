package com.merino.mypokemonapi.service

import com.merino.mypokemonapi.model.PokResponse
import com.merino.mypokemonapi.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon/{id}")
    fun getPokemonInfo(@Path("id")id: Int): Call<Pokemon>
    @GET("pokemon")
    fun getpokemonList(@Query("limit")llimit: Int, @Query("offset")offset: Int): Call<PokResponse>
}
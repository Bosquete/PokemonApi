package com.merino.mypokemonapi.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merino.mypokemonapi.model.PokResponse
import com.merino.mypokemonapi.model.PokeResult
import com.merino.mypokemonapi.service.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListViewModel() : ViewModel() {


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val pokemonList = MutableLiveData<List<PokeResult>>()

    fun getPokemonList() {
        val call = getRetrofit().create(ApiService::class.java).getpokemonList(100, 0)

        call.enqueue(object : Callback<PokResponse> {
            override fun onResponse(call: Call<PokResponse>, response: Response<PokResponse>) {
                response.body()?.results?.let { list ->
                    pokemonList.postValue(list)
                }
            }
            override fun onFailure(call: Call<PokResponse>, t: Throwable) {
                call.cancel()
            }

        })
    }
}
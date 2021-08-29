package com.merino.mypokemonapi.info

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.merino.mypokemonapi.R
import kotlinx.android.synthetic.main.activity_pokinfo.*

class InfoActivity: AppCompatActivity() {

    lateinit var viewModel: InfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokinfo)

        viewModel = ViewModelProvider(this).get(InfoViewModel::class.java)
        initUI()
    }

    private fun initUI() {
        val id = intent.extras?.get("id") as Int
        viewModel.getPokemonInfo(id)
        viewModel.pokemonInfo.observe(this, Observer { pokemon ->
            name_tv.text = pokemon.name
            height_tv.text = "Altura: ${pokemon.height/10.0}m"
            weight_tv.text = "Peso: ${pokemon.weight/10.0}"

            Glide.with(this).load(pokemon.sprites.frontDefault).into(image_view)
        })
    }
}
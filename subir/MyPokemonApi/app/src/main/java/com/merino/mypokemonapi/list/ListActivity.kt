package com.merino.mypokemonapi.list

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.merino.mypokemonapi.R
import com.merino.mypokemonapi.databinding.ActivityPokinfoBinding
import com.merino.mypokemonapi.databinding.ActivityPoklistBinding
import com.merino.mypokemonapi.info.InfoActivity
import kotlinx.android.synthetic.main.activity_poklist.*

class ListActivity : AppCompatActivity() {

    private lateinit var viewModel: ListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poklist)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        initUI()
    }

    private fun initUI() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = ListAdapter{
            val intent = Intent(this, InfoActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }

        viewModel.getPokemonList()
        viewModel.pokemonList.observe(this, Observer { list ->
            (recycler_view.adapter as ListAdapter).setData(list)
        })

    }
}
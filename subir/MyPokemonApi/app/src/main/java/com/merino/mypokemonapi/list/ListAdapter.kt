package com.merino.mypokemonapi.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.merino.mypokemonapi.R
import com.merino.mypokemonapi.model.PokeResult
import kotlinx.android.synthetic.main.activity_poksearch.view.*

class ListAdapter(val pokemonClick: (Int) -> Unit) :
    RecyclerView.Adapter<ListAdapter.SearchViewHolder>() {
    var pokemonList: List<PokeResult> = emptyList<PokeResult>()

    fun setData(list: List<PokeResult>) {
        pokemonList = list
        notifyDataSetChanged()
    }


    class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_poksearch, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.itemView.txt_tv.text = "#${position + 1} - ${pokemon.name}"
        holder.itemView.setOnClickListener { pokemonClick(position + 1) }
    }

    override fun getItemCount(): Int = pokemonList.size


}
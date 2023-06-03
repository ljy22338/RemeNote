package com.example.remenote.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.remenote.R
import com.example.remenote.main.DeckListFragment

class DeckAdapter (private val titleList: List<String>,private val fragment: DeckListFragment) :
    RecyclerView.Adapter<DeckAdapter.DeckHolder>() {

    private var noteListListener: DeckListListener = fragment

    interface DeckListListener {
        fun onDeckListItemClick(str:String)
    }

    inner class DeckHolder(view: View) : RecyclerView.ViewHolder(view) {
        val deckName: TextView = view.findViewById(R.id.text_deck_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckAdapter.DeckHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_deck, parent, false)
        return DeckHolder(view)
    }
    override fun getItemCount() = titleList.size
    override fun onBindViewHolder(holder: DeckHolder, position: Int) {
        holder.itemView.setOnClickListener {
            noteListListener.onDeckListItemClick( "")

        }
        val title = titleList[position]
        holder.deckName.text =title
    }

}
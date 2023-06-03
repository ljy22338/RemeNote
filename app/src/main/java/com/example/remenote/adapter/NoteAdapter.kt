package com.example.remenote.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.remenote.main.NoteListFragment
import com.example.remenote.R
import com.example.remenote.main.NoteListItem
import java.util.stream.Collectors


class NoteAdapter(private val fragment: NoteListFragment) :
    RecyclerView.Adapter<NoteAdapter.NewsHolder>() {


    var noteListItems= ArrayList<NoteListItem>()
        set(value) {
            noteListItems.clear()
            noteListItems.addAll(value)
            field=noteListItems.stream().distinct().collect(Collectors.toList()) as ArrayList
            notifyDataSetChanged()
        }

    private var noteListListener: NoteListListener = fragment

    interface NoteListListener {
        fun onNoteListItemClick(noteListItem: NoteListItem)
    }

    inner class NewsHolder(view: View) : RecyclerView.ViewHolder(view) {
        val newsTitle: TextView = view.findViewById(R.id.newsTitleTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NewsHolder(view)
    }
    override fun getItemCount() = noteListItems.size


    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.itemView.setOnClickListener {
            noteListListener.onNoteListItemClick( noteListItems[position])

        }
        val noteListItem=noteListItems[position]
        holder.newsTitle.text=noteListItem.note.title
    }

}
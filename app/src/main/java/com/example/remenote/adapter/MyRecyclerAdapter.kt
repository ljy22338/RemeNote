package com.example.remenote.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.remenote.R

class MyRecyclerAdapter (private val titleList: List<String>, fragment: MyListListener, private val str: String) :
    RecyclerView.Adapter<MyRecyclerAdapter.MyHolder>() {

    private var myListListener: MyListListener = fragment

    interface MyListListener {
        fun onMyListItemClick(str:String)
    }


    inner class MyHolder(view: View) : RecyclerView.ViewHolder(view) {
        val myTitle: TextView = view.findViewById(R.id.newsTitleTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return MyHolder(view)
    }
    override fun getItemCount() = titleList.size
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.itemView.setOnClickListener {
            myListListener.onMyListItemClick( str)

        }
        val title = titleList[position]
        holder.myTitle.text =title
    }

}
package com.example.remenote.main

import androidx.fragment.app.Fragment
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.remenote.R
import com.example.remenote.adapter.MyRecyclerAdapter
import com.example.remenote.adapter.NoteAdapter
import com.example.remenote.databinding.FragmentNoteListBinding
import com.example.remenote.databinding.FragmentPersonalBinding
import com.example.remenote.edit.EditActivity


class PersonalFragment : Fragment(),MyRecyclerAdapter.MyListListener {

    lateinit var binding:FragmentPersonalBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("", "onCreateView")
        binding= FragmentPersonalBinding.inflate(inflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.recyclerView.adapter= MyRecyclerAdapter(listOf("1","2","3"),this,"")
        binding.recyclerView.layoutManager=LinearLayoutManager(context)

        binding.recyclerView.addItemDecoration(CustomItemDecoration(1, Color.GRAY))
        val bgDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.recyclerview_bg)
        binding.recyclerView.background = bgDrawable

        return binding.root
    }

    override fun onMyListItemClick(str: String) {
        TODO("Not yet implemented")
    }


}
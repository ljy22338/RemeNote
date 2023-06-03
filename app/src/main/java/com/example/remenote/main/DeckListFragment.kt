package com.example.remenote.main

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.remenote.R
import com.example.remenote.adapter.DeckAdapter
import com.example.remenote.databinding.FragmentDeckListBinding

class DeckListFragment : Fragment() ,DeckAdapter.DeckListListener{
    lateinit var binding: FragmentDeckListBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("", "onCreateView")
        binding= FragmentDeckListBinding.inflate(inflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val toolbar = binding.toolbar
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)



        binding.recyclerView.adapter= DeckAdapter(listOf("1","2","3","4","5","6","7","1","2","3","4","5","6","7","1","2","3","4","5","6","7"),this)
        binding.recyclerView.layoutManager= LinearLayoutManager(context)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        binding.recyclerView.addItemDecoration(CustomItemDecoration(1, Color.GRAY))
        val bgDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.recyclerview_bg)
        binding.recyclerView.background = bgDrawable

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_refresh->{}
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDeckListItemClick(str: String) {
        TODO("Not yet implemented")
    }
}
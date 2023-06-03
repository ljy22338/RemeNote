package com.example.remenote.edit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.remenote.R
import com.example.remenote.databinding.FragmentContentBinding

class ContentFragment : Fragment() {

    private lateinit var binding: FragmentContentBinding
    private val viewModel: EditNoteViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentContentBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        viewModel.inputContent.observe(viewLifecycleOwner) { text ->
            binding.newsContent.text = text
        }
        viewModel.inputTitle.observe(viewLifecycleOwner) {text->
            binding.newsTitle.text=text
        }
    }



}
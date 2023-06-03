package com.example.remenote.edit

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.remenote.databinding.FragmentEditBinding
import com.example.remenote.util.FileUtils
import java.io.File

class EditFragment : Fragment() {

    private lateinit var binding:FragmentEditBinding
    private val viewModel: EditNoteViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentEditBinding.inflate(layoutInflater)

//        binding.editTextContent.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//                FileUtils().saveContent(requireContext(),binding.editTextTitle.text.toString(),binding.editTextContent.text.toString())
//                viewModel.inputContent.value = s.toString()
//            }
//            override fun afterTextChanged(s: Editable) {}
//        })
        binding.editTextTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                FileUtils().saveTitle(requireContext(),binding.editTextTitle.text.toString())
                viewModel.inputTitle.value = s.toString()
            }
            override fun afterTextChanged(s: Editable) {}
        })
        val filePath=viewModel.intent.getStringExtra("notePath")
        val file = filePath?.let { File(it) }
        if (file != null) {
//            binding.editTextContent.setText(file.readText())
            binding.editTextTitle.setText(file.name)
        }
        val webView =  binding.editTextContent
        webView.settings.javaScriptEnabled = true

        webView.loadUrl("file:///android_asset/eidtor.html");


        return binding.root
    }

}
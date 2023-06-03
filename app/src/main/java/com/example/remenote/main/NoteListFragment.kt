package com.example.remenote.main


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.database.ContentObserver
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.remenote.Note
import com.example.remenote.R
import com.example.remenote.adapter.DeckAdapter
import com.example.remenote.adapter.NoteAdapter
import com.example.remenote.databinding.FragmentNoteListBinding
import com.example.remenote.edit.EditActivity
import com.example.remenote.util.FileUtils

class NoteListFragment : NavHostFragment(), NoteAdapter.NoteListListener {
    lateinit var binding: FragmentNoteListBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: NoteAdapter
    private lateinit var receiver: BroadcastReceiver
    private lateinit var contentObserver: ContentObserver


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("", "onCreateView")
        binding = FragmentNoteListBinding.inflate(inflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)




        adapter= NoteAdapter(this)

        val files =FileUtils().getCustomFolder(requireContext(),"note").listFiles()?.toList()

        val noteListItems= ArrayList<NoteListItem>().apply {
            if (files != null) {
                for (filePath in files){
                    add(NoteListItem(Note(0,filePath.name,filePath.path),requireContext()))
                }
            }
        }
        adapter.noteListItems = noteListItems


        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        binding.recyclerView.addItemDecoration(CustomItemDecoration(1, Color.GRAY))
        val bgDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.recyclerview_bg)
        binding.recyclerView.background = bgDrawable

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        requireContext().unregisterReceiver(receiver)
    }

    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter(Intent.ACTION_MEDIA_SCANNER_FINISHED)
        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val files = FileUtils().getCustomFolder(requireContext(),"note").listFiles()?.toList()
                val noteListItems= ArrayList<NoteListItem>().apply {
                    if (files != null) {
                        for (filePath in files){
                            add(NoteListItem(Note(0,filePath.name,filePath.path),requireContext()))
                        }
                    }
                }
                adapter.noteListItems = noteListItems
            }
        }
        requireContext().registerReceiver(receiver, intentFilter)
    }

    override fun onNoteListItemClick(noteListItem: NoteListItem) {
        val i = Intent(requireActivity(), EditActivity::class.java)
        i.putExtra("notePath", noteListItem.getNoteFile()?.path)
        startActivity(i)
    }



}
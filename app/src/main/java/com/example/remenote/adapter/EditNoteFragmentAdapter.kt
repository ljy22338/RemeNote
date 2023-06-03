package com.example.remenote.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.remenote.edit.ContentFragment
import com.example.remenote.edit.EditFragment

class EditNoteFragmentAdapter (activity: FragmentActivity): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> EditFragment()
            1-> ContentFragment()
            else -> {throw IllegalArgumentException("Invalid position $position")}
        }
    }
}
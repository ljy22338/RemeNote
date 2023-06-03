package com.example.remenote.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.remenote.main.DeckListFragment
import com.example.remenote.main.NoteListFragment
import com.example.remenote.main.PersonalFragment
import com.example.remenote.main.TestFragment

class MainFragmentAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> NoteListFragment()
            1-> DeckListFragment()
//            2-> PersonalFragment()
            2-> TestFragment()
            else -> {throw IllegalArgumentException("Invalid position $position")}
        }
    }
}
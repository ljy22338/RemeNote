package com.example.remenote.edit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.remenote.R
import com.example.remenote.adapter.EditNoteFragmentAdapter
import com.example.remenote.databinding.ActivityEditBinding


class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding
    private lateinit var viewModel:EditNoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[EditNoteViewModel::class.java]
        viewModel.intent=intent
        setContentView(binding.root)

        val viewPager = binding.viewPager
        val tabLayout = binding.bottomNavigation
        viewPager.adapter = EditNoteFragmentAdapter(this)
        tabLayout.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_edit -> viewPager.setCurrentItem(0, true)
                R.id.menu_content -> viewPager.setCurrentItem(1, true)

            }
            true
        }
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> tabLayout.selectedItemId = R.id.menu_edit
                    1 -> tabLayout.selectedItemId = R.id.menu_content

                }
            }
        })
    }

}
package com.example.remenote.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.remenote.R
import com.example.remenote.adapter.MainFragmentAdapter
import com.example.remenote.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager=binding.viewPager
        val tabLayout=binding.bottomNavigation
        viewPager.adapter= MainFragmentAdapter(this)
        tabLayout.setOnItemSelectedListener {item->
            when(item.itemId){
                R.id.menu_note ->viewPager.setCurrentItem(0,true)
                R.id.menu_deck ->viewPager.setCurrentItem(1,true)
                R.id.menu_personal ->viewPager.setCurrentItem(2,true)
            }
            true
        }


        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> tabLayout.selectedItemId = R.id.menu_note
                    1 -> tabLayout.selectedItemId = R.id.menu_deck
                    2 -> tabLayout.selectedItemId = R.id.menu_personal
                }
            }
        })

        }
}
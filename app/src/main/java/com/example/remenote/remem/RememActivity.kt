package com.example.remenote.remem

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.remenote.databinding.ActivityRememBinding

class RememActivity : AppCompatActivity() {
    lateinit var binding: ActivityRememBinding
    lateinit var viewModel: RememViewModel
    private lateinit var fragmentManager: FragmentManager
    private lateinit var group: Group

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParameter()
        setContentView(binding.root)
        cardFront(viewModel.counter)
        bindButton()

    }


    private fun initParameter() {
        binding = ActivityRememBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[RememViewModel::class.java]
        fragmentManager = supportFragmentManager
        viewModel.counter = 0
//        group = getGroup()
    }
//
//    private fun getGroup(): Group {
//        val gson = Gson()
//        val groupName = intent.getStringExtra("groupName")
//        val folder = this.getDir("groups", Context.MODE_PRIVATE)
//        val file = groupName?.let { File(folder, it) }
//        val groupStr = FileReader(file).buffered().readText()
//        return gson.fromJson(groupStr, Group::class.java)
//    }
    private fun bindButton(){
        binding.easy.setOnClickListener { cardFront(++viewModel.counter) }
        binding.normal.setOnClickListener { cardFront(++viewModel.counter) }
        binding.difficulty.setOnClickListener { cardFront(++viewModel.counter) }
        binding.redo.setOnClickListener { cardFront(++viewModel.counter) }
        binding.display.setOnClickListener { cardBehind(viewModel.counter) }
    }

    private fun cardFront(index: Int) {
//        binding.cardName.text = group.cardList[index].name

        binding.cardName.visibility = View.VISIBLE
        binding.display.visibility = View.VISIBLE

        binding.cardContent.visibility = View.INVISIBLE
        binding.easy.visibility = View.INVISIBLE
        binding.normal.visibility = View.INVISIBLE
        binding.difficulty.visibility = View.INVISIBLE
        binding.redo.visibility = View.INVISIBLE

    }

    private fun cardBehind(index: Int) {
//        binding.cardName.text = group.cardList[index].name
//        binding.cardContent.text = group.cardList[index].content

        binding.display.visibility = View.INVISIBLE

        binding.cardName.visibility = View.VISIBLE
        binding.cardContent.visibility = View.VISIBLE
        binding.easy.visibility = View.VISIBLE
        binding.normal.visibility = View.VISIBLE
        binding.difficulty.visibility = View.VISIBLE
        binding.redo.visibility = View.VISIBLE
    }



}
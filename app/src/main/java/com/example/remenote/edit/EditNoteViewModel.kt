package com.example.remenote.edit

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditNoteViewModel : ViewModel() {
     val inputContent = MutableLiveData<String>()
     val inputTitle = MutableLiveData<String>()
     lateinit var intent:Intent

}
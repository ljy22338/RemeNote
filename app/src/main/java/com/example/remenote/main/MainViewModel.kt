package com.example.remenote.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.Context


class MainViewModel () : ViewModel() {
    var clickedNoteName: String? =null
    val noteList=MutableLiveData<NoteListItem>()


}
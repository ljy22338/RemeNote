package com.example.remenote.main

import android.content.Context
import android.database.ContentObserver
import android.os.FileObserver
import com.example.remenote.Note
import com.example.remenote.util.FileUtils
import java.io.File

class NoteListItem (val note:Note,val context:Context){
    fun getNoteFile(): File? {
        val dataFolder=FileUtils().getCustomFolder(context,"note")
        val files= dataFolder.listFiles { file ->file.name== note.fileName}
        return if(files!=null){
            if(files.isNotEmpty()){
                files[0]}else{
                    null
                }
        }else{
            null
        }

    }
}
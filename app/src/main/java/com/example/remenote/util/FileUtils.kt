package com.example.remenote.util

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedWriter
import java.io.File
import java.io.IOException
import java.io.OutputStreamWriter

/*
方法
- 将文件储存在应用file目录下
- 将文件储存在groups目录下
- 删除file目录下的文件
- 删除group目录下的文件
 */
class FileUtils {

    fun saveContent(context: Context, saveTitle: String, saveContent: String) {
        try {
            val folder=File(context.filesDir.toString()+"note")
            val file = File(folder,saveTitle)
            val output=file.outputStream()
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(saveContent)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    fun saveTitle(context: Context, saveTitle: String) {
        try {
            val folder=File(context.filesDir.toString()+"note")
            val file = File(folder,saveTitle)
            file.renameTo(File(file.parent,saveTitle))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun saveToCustomFolder(
        context: Context,
        custom: String,
        saveTitle: String,
        saveContent: String
    ) {
        try {
            val folder = File(context.filesDir, custom)
            folder.mkdirs()
            val file = File(folder, saveTitle)
            val writer = file.writer()
            writer.write(saveContent)
            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun readTextFromCustomFolder(context: Context, customFolder: String, fileName: String):String {
        lateinit var text:String
        try {
            var str="$customFolder/$fileName"
            if (customFolder==""){
                str= fileName
            }

            val file = File(context.filesDir,str )
            val reader = file.reader()
            text= reader.readText()
            reader.close()
            return text
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return text
    }
    fun getCustomFolderPath(context: Context, customFolder: String):String{

        return context.filesDir.toString()+customFolder
    }

    fun getCustomFolder(context: Context,subdirectory:String):File {
        val subfolder: File = File(context.filesDir, subdirectory)
        if (!subfolder.exists()) {
            subfolder.mkdir() // 如果子文件夹不存在就创建
        }
        return subfolder
    }
}
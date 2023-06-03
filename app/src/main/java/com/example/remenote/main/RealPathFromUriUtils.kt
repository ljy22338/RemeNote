package com.example.remenote.main

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.provider.OpenableColumns
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream


object RealPathFromUriUtils {
    fun getRealPathFromUri(context: Context, uri: Uri?): String? {
        if (uri == null) {
            return null
        }
        var realPath: String? = null
        val scheme = uri.scheme
        if (scheme == "content") {
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    val index = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                    if (index != -1) {
                        realPath = cursor.getString(index)
                    }
                    }
                cursor.close()
            }
        } else if (scheme == "file") {
            realPath = uri.path
        }
        return realPath
    }
    fun copyFileToPrivateStorage(context: Context, uri: Uri?): String? {
        if (uri == null) {
            return null
        }
        val inputStream = context.contentResolver.openInputStream(uri)
        val fileName = getFileName(context, uri) ?: return null
        val file = File(context.getFilesDir() ,fileName)
        val outputStream = FileOutputStream(file)
        val buffer = ByteArray(1024)
        var read: Int
        while (inputStream?.read(buffer).also { read = it!! } != -1) {
            outputStream.write(buffer, 0, read)
        }
        inputStream?.close()
        outputStream.flush()
        outputStream.close()
        return Uri.fromFile(file).toString()
    }
    private fun getFileName(context: Context, uri: Uri): String? {
        var name: String? = null
        val returnCursor = context.contentResolver.query(uri, null, null, null, null)
        if (returnCursor != null) {
            val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            returnCursor.moveToFirst()
            name = returnCursor.getString(nameIndex)
            returnCursor.close()
        }
        return name
    }
}
package com.example.remenote.main

import android.database.ContentObserver
import android.os.Handler

class MyContentObserver(private val handler: Handler?) :ContentObserver(handler) {
    override fun onChange(selfChange: Boolean) {
        super.onChange(selfChange)

    }
}
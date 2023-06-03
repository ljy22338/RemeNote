package com.example.remenote

data class Note (
    val id :Int,
    val title:String,
    val fileName:String,
    val createdTime:Long
){
    constructor(id: Int, title: String, fileName: String) :
            this(id, title, fileName, System.currentTimeMillis())
}
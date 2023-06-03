package com.example.remenote.note_analyzer

class CardTag : Tag {
    override val prefix:String="#"
    override val name:String="card"
    override val tag:String= "$prefix$name"
}
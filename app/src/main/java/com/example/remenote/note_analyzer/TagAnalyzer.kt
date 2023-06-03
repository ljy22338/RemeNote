package com.example.remenote.note_analyzer

import android.util.Log


/*

属性
- 笔记中的所有标签
- 笔记中所有被标记的段落
- 笔记的内容
- 笔记的标题
- 笔记的路径
方法
- 提取某一类标签下的一段或多段文字
- 提取某一个标签下的一段或多段文字
- 获取某一类标签的行号
- 获取某一个标签的行号

 */
class TagAnalyzer<T: Tag>(private val notes: String, private val tag:T) {
    fun getParagraphsList(): List<List<String>> {
        val regex=Regex(tag.tag+"\\s+([\\s\\S]*?)\\s+([\\s\\S]*?)(?=#card|$)")
        Log.d("",tag.tag+"\\s+([\\s\\S]*?)\\s+([\\s\\S]*?)(?=#card|$)")
        val results = regex.findAll(notes)
        return ArrayList<List<String>>().apply {
            for (result in results){
                add(listOf(result.groupValues[1],result.groupValues[2]))
            }
        }
    }

}
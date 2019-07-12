package com.mayank.room

interface TodoItemClickListener {

    fun onDeleteClick(task:Task,position:Int)

    fun onDoneClick(task:Task,position:Int)
}
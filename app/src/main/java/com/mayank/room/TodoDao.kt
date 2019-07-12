package com.mayank.room

import androidx.room.Dao
import androidx.room.Insert

interface TodoDao {

    @Insert
    fun insertRow(task:Task)

    @Insert
    fun insertMultiple(tasklist:ArrayList<Task>)
}
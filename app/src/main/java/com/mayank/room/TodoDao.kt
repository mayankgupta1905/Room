package com.mayank.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {
// why we use interface- think
    @Insert
    fun insertRow(task:Task)

    @Insert
    fun insertMultiple(tasklist:ArrayList<Task>)

    @Query("Select * FROM Task")
    fun getAllTask() : List<Task>

    @Query("Select * FROM Task Where status = :done")
    fun getAllDoneTask(done:Boolean) : List<Task>

    @Delete
    fun deleteTask(task : Task)
}
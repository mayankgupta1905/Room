package com.mayank.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
// why we use interface- think
    @Insert
    fun insertRow(task:Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE) // when there is a conflict in the primary keys then we use this to avoid crash
    fun insertMultiple(tasklist:ArrayList<Task>)

//    @Query("Select * FROM Task")
//    fun getAllTask() : List<Task>

    @Query("Select * FROM Task")
    fun getAllTask() : LiveData<List<Task>> // using live data

    @Query("Select * FROM Task Where status = :done")
    fun getAllDoneTask(done:Boolean) : List<Task>

    @Delete
    fun deleteTask(task : Task)
}
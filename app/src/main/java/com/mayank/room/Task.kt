package com.mayank.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity                         //this makes a table whose parameters are the below mentioned
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id:Long? = null,
  //  @ColumnInfo(name = "Description") // it changes the name of the column in the SQL and is used when to see the row database  and for queries
    val task:String, // any reference other than the like when to display the result we have to use the task variable
    val status:Boolean
)
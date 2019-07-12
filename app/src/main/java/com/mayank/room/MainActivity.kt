package com.mayank.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    val db by lazy {
        Room.databaseBuilder(this,
            AppDatabase::class.java,
            "task.db")
            .allowMainThreadQueries() // to run small queries on main thread
            .fallbackToDestructiveMigration() //it avoids the crash when updated the schema of the database //it deletes the user db instead of crashing the app if any migration code is not defined
            .build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db.todoDao().getAllTask()
    }
}

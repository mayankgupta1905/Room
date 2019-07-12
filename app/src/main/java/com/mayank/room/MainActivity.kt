package com.mayank.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var task_list = arrayListOf<Task>()
    val db by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "task.db"
        )
            .allowMainThreadQueries() // to run small queries on main thread
            .fallbackToDestructiveMigration() //it avoids the crash when updated the schema of the database //it deletes the user db instead of crashing the app if any migration code is not defined
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        task_list = db.todoDao().getAllTask().filter { todo ->
            todo.task.contains("abc",true)
        } as ArrayList<Task>

        val adapter = TaskAdapter(task_list, this)
        rvTask.layoutManager = LinearLayoutManager(this)
        rvTask.adapter = adapter

        adapter.todoItemClickListener = object: TodoItemClickListener {
            override fun onDeleteClick(task: Task, position: Int) {
                db.todoDao().deleteTask(task)
                task_list = db.todoDao().getAllTask() as ArrayList<Task>
                adapter.updateTasks(task_list)
            }

            override fun onDoneClick(task: Task, position: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }

        add.setOnClickListener {
            db.todoDao().insertRow(Task(task = Task_input.text.toString(), status = false))
            task_list = db.todoDao().getAllTask() as ArrayList<Task>
            adapter.updateTasks(task_list)
            adapter.notifyDataSetChanged()
            Task_input.text.clear()
        }
    }
}
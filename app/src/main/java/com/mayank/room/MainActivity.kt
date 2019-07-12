package com.mayank.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var task_list = arrayListOf<Task>()

    var formData: MediatorLiveData<Boolean> = MediatorLiveData()

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
        val adapter = TaskAdapter(task_list, this)
        rvTask.layoutManager = LinearLayoutManager(this)
        rvTask.adapter = adapter

        //returns value in synchronous manner
//        db.todoDao().getAllTask().value
  //Returns value in Async Manner

        formData.addSource(db.todoDao().getAllTask()) {
            if(it.isNotEmpty())
            {
                formData.value = false
            }else
            {
                formData.value = true
                formData.removeSource(db.todoDao().getAllTask())
            }
        }


        db.todoDao().getAllTask().observe(this, Observer {
            task_list = it as ArrayList<Task>
            adapter.updateTasks(task_list)
        })

//        task_list = db.todoDao().getAllTask().filter { todo ->
//            todo.task.contains("abc",true)
//        } as ArrayList<Task>


        adapter.todoItemClickListener = object: TodoItemClickListener {
            override fun onDeleteClick(task: Task, position: Int) {
                db.todoDao().deleteTask(task)
            }

            override fun onDoneClick(task: Task, position: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }

        add.setOnClickListener {
            db.todoDao().insertRow(Task(task = Task_input.text.toString(), status = false))
            Task_input.text.clear()
        }
    }
}
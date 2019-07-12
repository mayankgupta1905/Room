package com.mayank.room

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task_item.view.*

class TaskAdapter(val task_list:ArrayList<Task>,val context: Context): RecyclerView.Adapter<TaskViewHolder>()
{
    lateinit var todoItemClickListener:TodoItemClickListener
    fun updateTasks(newTasks: ArrayList<Task>) {
        task_list.clear()
        task_list.addAll(newTasks)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflator = LayoutInflater.from(context)
        val view = inflator.inflate(R.layout.task_item,parent,false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int = task_list.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task_item = task_list[position]
        holder.itemView.apply{
            task_output.text = task_item.task
            Done.isChecked  = task_item.status
        }
        holder.itemView.delete_item.setOnClickListener {
            todoItemClickListener.onDeleteClick(task_item,position)
        }
        holder.itemView.Done.setOnClickListener {
            todoItemClickListener.onDoneClick(task_item,position)
        }
    }
}

class TaskViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview)
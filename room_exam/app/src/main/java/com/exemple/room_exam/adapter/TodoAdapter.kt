package com.exemple.room_exam.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.exemple.room_exam.R
import com.exemple.room_exam.models.Todo


class TodoAdapter(val context: Context, val itemList: List<Todo>) : RecyclerView.Adapter<TodoAdapter.Holder>() {


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id = itemView?.findViewById<TextView>(R.id.item_num)
        val title = itemView?.findViewById<TextView>(R.id.item_todo)

        fun bind(todo: Todo) {
            id?.text = todo.id.toString()
            title?.text = todo.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(itemList[position])
    }


}
package com.exemple.room_exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.exemple.room_exam.models.Todo
import com.exemple.room_exam.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.getAll().observe(this, Observer {
            textview.text = it.toString()
        })

        btn_add.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) { // Dispatchers.IO : Background Thread
                viewModel.insert(Todo(todo_edit.text.toString()))
            }
        }

        btn_delete.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) { // Dispatchers.IO : Background Thread
                viewModel.delete(Todo(todo_edit.text.toString()))
            }
        }

    }


}

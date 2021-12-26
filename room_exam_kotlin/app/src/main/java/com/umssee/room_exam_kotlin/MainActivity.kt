package com.umssee.room_exam_kotlin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val todoEdit = findViewById<EditText>(R.id.todo_edit)
        val resultText = findViewById<TextView>(R.id.result_text)
        val addButton = findViewById<Button>(R.id.add_button)

        mainViewModel.getAll().observe(this, Observer {
            resultText.text = it.toString()
        })

        addButton.setOnClickListener {
            mainViewModel.insert(Todo(todoEdit.text.toString()))
        }
    }
}
package com.umssee.room_exam_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.room.Room

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "todo-db-kotlin"
        ).allowMainThreadQueries().build()

        val todoEdit = findViewById<EditText>(R.id.todo_edit)
        val resultText = findViewById<TextView>(R.id.result_text)
        val addButton = findViewById<Button>(R.id.add_button)
        resultText.text = db.todoDao().getAll().toString()

        addButton.setOnClickListener {
            db.todoDao().insert(Todo(todoEdit.text.toString()))
            resultText.text = db.todoDao().getAll().toString()
         }

    }
}
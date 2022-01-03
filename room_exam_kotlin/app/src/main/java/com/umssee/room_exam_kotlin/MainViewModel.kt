package com.umssee.room_exam_kotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val db: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "todo-db-kotlin"
    ).build()
    var todos: LiveData<List<Todo>>
    var newTodo: String = ""

    init {
        todos = getAll()
    }

    private fun getAll(): LiveData<List<Todo>> {
        return db.todoDao().getAll()
    }

    fun insert(todo: String) {
        viewModelScope.launch(Dispatchers.IO) {
            db.todoDao().insert(Todo(todo))
        }
    }

}
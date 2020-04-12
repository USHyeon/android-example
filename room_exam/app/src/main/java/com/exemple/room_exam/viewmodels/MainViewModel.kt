package com.exemple.room_exam.viewmodels

import android.app.Application
import androidx.lifecycle.*
import androidx.room.Room
import com.exemple.room_exam.AppDatabase
import com.exemple.room_exam.models.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "database-name"
    ).build()

    var todos: LiveData<List<Todo>>
    var newTodo: String

    init {
        todos = getAll()
        newTodo = ""
    }

    fun getAll(): LiveData<List<Todo>> {
        return db.todoDao().getAll()
    }

    fun insert(todo: String) {
        viewModelScope.launch(Dispatchers.IO) {
            db.todoDao().insert(Todo(todo))
        }
    }

    fun delete(todo: String) {
        viewModelScope.launch(Dispatchers.IO){
            db.todoDao().delete(todo)
        }
    }
}
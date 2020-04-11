package com.exemple.room_exam.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.exemple.room_exam.AppDatabase
import com.exemple.room_exam.models.Todo

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "database-name"
    ).build()

    fun getAll(): LiveData<List<Todo>> {
        return db.todoDao().getAll()
    }

    suspend fun insert(todo: Todo) {
        return db.todoDao().insert(todo)
    }

    suspend fun delete(todo: Todo) {
        return db.todoDao().delete(todo)
    }
}
package com.exemple.room_exam

import androidx.room.Database
import androidx.room.RoomDatabase
import com.exemple.room_exam.models.Todo

@Database(entities = arrayOf(Todo::class), version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun todoDao(): TodoDao
    }
    
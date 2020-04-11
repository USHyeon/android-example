package com.exemple.room_exam

import androidx.lifecycle.LiveData
import androidx.room.*
import com.exemple.room_exam.models.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo")
    fun getAll(): LiveData<List<Todo>>

    @Insert
    fun insert(todo: Todo)

    @Update
    fun update(todo: Todo)

    @Delete
    fun delete(todo: Todo)


}
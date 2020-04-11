package com.exemple.room_exam.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo (
    @ColumnInfo(name = "column_title") var title: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
package com.example.hiltinject.ui.second

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hiltinject.R
import com.example.hiltinject.ui.data.MyRepository

class SecondActivity : AppCompatActivity() {

    val repository = MyRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }
}
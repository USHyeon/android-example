package com.example.counterapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var tvCounter: TextView
    lateinit var btnIncrease: Button
    lateinit var btnDecrease: Button

    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCounter = findViewById(R.id.tvCounter)
        btnIncrease = findViewById(R.id.btnIncrease)
        btnDecrease = findViewById(R.id.btnDecrease)

        btnIncrease.setOnClickListener {
            count++
            tvCounter.text = "$count"
        }
        btnDecrease.setOnClickListener {
            count--
            tvCounter.text = "$count"
        }
    }
}
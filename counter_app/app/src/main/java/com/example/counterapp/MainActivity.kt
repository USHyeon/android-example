package com.example.counterapp

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private val mainViewModel: MainViewModel by viewModels()

    lateinit var tvCounter: TextView
    lateinit var btnIncrease: Button
    lateinit var btnDecrease: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate: ")

        tvCounter = findViewById(R.id.tvCounter)
        btnIncrease = findViewById(R.id.btnIncrease)
        btnDecrease = findViewById(R.id.btnDecrease)

        tvCounter.text = "${mainViewModel.count}"

        btnIncrease.setOnClickListener {
            mainViewModel.count++
            tvCounter.text = "${mainViewModel.count}"
        }
        btnDecrease.setOnClickListener {
            mainViewModel.count--
            tvCounter.text = "${mainViewModel.count}"
        }

        // 화면 초기화 확인
        registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Log.d(TAG, "onActivityCreated: ")
            }

            override fun onActivityStarted(activity: Activity) {
                Log.d(TAG, "onActivityStarted: ")
            }

            override fun onActivityResumed(activity: Activity) {
                Log.d(TAG, "onActivityResumed: ")
            }

            override fun onActivityPaused(activity: Activity) {
                Log.d(TAG, "onActivityPaused: ")
            }

            override fun onActivityStopped(activity: Activity) {
                Log.d(TAG, "onActivityStopped: ")
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                Log.d(TAG, "onActivitySaveInstanceState: ")
            }

            override fun onActivityDestroyed(activity: Activity) {
                Log.d(TAG, "onActivityDestroyed: ")
            }

        })
    }
}
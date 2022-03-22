package com.example.hiltinject.ui.second

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.hiltinject.R
import com.example.hiltinject.data.MyRepository
import com.example.hiltinject.di.ApplicationModule
import com.example.hiltinject.di.qualifier.ActivityHash
import com.example.hiltinject.di.qualifier.AppHash
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: MyRepository

    @AppHash
    @Inject
    lateinit var applicationHash: String

    @ActivityHash
    @Inject
    lateinit var activityHash: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        Log.d("SecondActivity", "${repository.hashCode()}")
        Log.d("SecondActivity", "appHash: $applicationHash")
        Log.d("SecondActivity", "ActivityHash: $activityHash")
    }
}
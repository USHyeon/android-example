package com.umssung.kioskmodeexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_kiosk_on.setOnClickListener {
            startLockTask()
        }

        btn_kiosk_off.setOnClickListener {
            stopLockTask()
        }

//        startActivity(Intent(this, FirstActivity::class.java))
    }

    override fun onBackPressed() {

    }
}
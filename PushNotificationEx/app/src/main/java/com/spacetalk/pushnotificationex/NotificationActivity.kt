package com.spacetalk.pushnotificationex

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val notiData = intent.getStringExtra("noti_data")

        AlertDialog.Builder(this)
            .setTitle("알림")
            .setMessage(notiData)
            .setPositiveButton("확인", null)
            .create()
            .show()
    }
}
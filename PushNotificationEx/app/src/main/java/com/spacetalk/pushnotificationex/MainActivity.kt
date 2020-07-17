package com.spacetalk.pushnotificationex

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.google.firebase.iid.FirebaseInstanceId
import com.onesignal.OneSignal
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())

        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Timber.d("FCM Log, getInstanceId failed ${task.exception}")
                return@addOnCompleteListener
            }

            val token = task.result?.token
            Timber.d("FCM Log, FCM 토큰 $token")
            Toast.makeText(this, token, Toast.LENGTH_SHORT).show()
        }

        OneSignal.idsAvailable { userId, registrationId ->
            var text = "OneSignal UserId:\n$userId\n\n"

            text += if (registrationId != null) {
                "Google Registration Id:\n$registrationId"
            } else {
                "Google Registration Id:\nCould not subscribe for push"
            }
            textView.text = text
        }

        button.setOnClickListener {
            //알림 채널 구성 및 생성
            val mNoti = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val id = "my_channel_01"
            val name = "channel name"
            val desc = "channel description text"
            val importance = NotificationManager.IMPORTANCE_LOW
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //알림채널 생성
                val mChannel = NotificationChannel(id, name, importance).apply {
                    this.description = desc
                    this.enableLights(true)
                    this.lightColor = Color.RED
                }
                mNoti.createNotificationChannel(mChannel)
            }

            val notifyID = 1
            val CHANNEL_ID = "my_channel_01"
            val style = NotificationCompat.BigTextStyle()
            style.bigText("Big Text")
            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("New Message")
                .setContentText("Android message test")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setStyle(style)
                .build()

            mNoti.notify(notifyID, notification)
        }
        button2.setOnClickListener {
            OneSignal.sendTag(
                "testKey",
                "testValue"
            )

        }
        button3.setOnClickListener {

        }
        button4.setOnClickListener {

        }
    }

}
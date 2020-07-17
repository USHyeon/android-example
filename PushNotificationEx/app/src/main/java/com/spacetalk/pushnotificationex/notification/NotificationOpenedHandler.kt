package com.spacetalk.pushnotificationex.notification

import android.app.Application
import android.content.Intent
import com.onesignal.OSNotificationOpenResult
import com.onesignal.OneSignal
import com.spacetalk.pushnotificationex.NotificationActivity
import timber.log.Timber

class NotificationOpenedHandler(private val application: Application) : OneSignal.NotificationOpenedHandler {

    //알림을 통해 앱을 실행할 경우 호출됨
    override fun notificationOpened(result: OSNotificationOpenResult?) {
        result?.let { result ->
            Timber.d("OneSignal Opened ${result.notification.payload.title}")
            Timber.d("OneSignal Opened ${result.notification.payload.body}")

            Timber.d("OneSignal Opened ${result.action.type}")
            Timber.d("OneSignal Opened ${result.action.actionID}")

            startApp(result.notification.payload.body)
        }
    }

    private fun startApp(message: String) {
        val intent = Intent(application, NotificationActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("noti_data", message)
        application.startActivity(intent)
    }
}
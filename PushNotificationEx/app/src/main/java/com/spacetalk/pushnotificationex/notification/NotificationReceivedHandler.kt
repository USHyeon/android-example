package com.spacetalk.pushnotificationex.notification

import com.onesignal.OSNotification
import com.onesignal.OneSignal
import timber.log.Timber

class NotificationReceivedHandler : OneSignal.NotificationReceivedHandler {
    //알림을 받을 때 호출됨
    override fun notificationReceived(notification: OSNotification?) {
        val data = notification?.payload?.additionalData
        Timber.d("OneSignal Received; notification: ${notification}")
        Timber.d("OneSignal Received; groupedNotifications: ${notification?.groupedNotifications}")
        Timber.d("OneSignal Received; title: ${notification?.payload?.title}")
        Timber.d("OneSignal Received; groupKey: ${notification?.payload?.groupKey}")
        Timber.d("OneSignal Received; groupMessage: ${notification?.payload?.groupMessage}")
        Timber.d("OneSignal Received; templateName: ${notification?.payload?.templateName}")
        Timber.d("OneSignal Received; priority: ${notification?.payload?.priority}")
        Timber.d("OneSignal Received; body: ${notification?.payload?.body}")

        val customKey: String

        if (data != null) {
            customKey = data.optString("notification_id", null)
            if (customKey != null)
                Timber.d("OneSignal Received; customKey: $customKey")
        }
    }
}
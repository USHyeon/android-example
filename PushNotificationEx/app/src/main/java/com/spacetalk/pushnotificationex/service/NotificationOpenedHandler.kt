package com.spacetalk.pushnotificationex.service

import com.onesignal.OSNotificationOpenResult
import com.onesignal.OneSignal
import timber.log.Timber

class NotificationOpenedHandler : OneSignal.NotificationOpenedHandler {
    //알림을 통해 앱을 실행할 경우 호출됨
    override fun notificationOpened(result: OSNotificationOpenResult?) {
        result?.let { result ->
            Timber.d("OneSignal Opened ${result}")
            Timber.d("OneSignal Opened ${result.notification.payload.title}")
            Timber.d("OneSignal Opened ${result.notification.payload.body}")
        }
    }
}
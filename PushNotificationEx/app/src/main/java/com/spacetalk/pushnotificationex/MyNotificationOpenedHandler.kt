package com.spacetalk.pushnotificationex

import com.onesignal.OSNotificationAction
import com.onesignal.OSNotificationOpenResult
import com.onesignal.OneSignal
import timber.log.Timber

class MyNotificationOpenedHandler : OneSignal.NotificationOpenedHandler {
    override fun notificationOpened(result: OSNotificationOpenResult) {
        val actionType = result.action.type
        val data = result.notification.payload.additionalData

        val customKey = data?.optString("customkey", null)
        if (customKey != null) {
            Timber.i("OneSignal, customkey set with value: $customKey")
        }

        if ( actionType == OSNotificationAction.ActionType.ActionTaken) {
            Timber.i("OneSignal, Button pressed with id: ${result.action.actionID}")
        }
    }
}
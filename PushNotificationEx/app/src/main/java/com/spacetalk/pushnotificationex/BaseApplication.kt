package com.spacetalk.pushnotificationex

import android.app.Application
import android.content.Context
import com.onesignal.OneSignal

class BaseApplication : Application() {
    companion object {
        lateinit var instance: Context private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.DEBUG, OneSignal.LOG_LEVEL.WARN)

        // OneSignal Initialization
        OneSignal.startInit(this)
            .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
            .unsubscribeWhenNotificationsAreDisabled(true)
            .init();
    }
}
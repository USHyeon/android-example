package com.test.st_hub

import android.app.Activity
import android.app.Application
import android.content.Intent
import com.tuya.smart.home.sdk.TuyaHomeSdk


class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        TuyaHomeSdk.setDebugMode(BuildConfig.DEBUG)
        TuyaHomeSdk.init(this)

        // 장기간 작동하지 않으면 45일 뒤에 세션이 무효화 되며 사용자 앱에서 로그아웃 된 뒤 다시 로그인 해야
        TuyaHomeSdk.setOnNeedLoginListener { context ->
            val intent = Intent(context, LoginActivity::class.java)
            if (context !is Activity) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
        }
    }
}
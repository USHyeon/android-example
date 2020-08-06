package com.umssung.androidthingstest01

import android.app.Activity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.umssung.animationtestexample.ui.animation.FloatingAnimation
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Skeleton of an Android Things activity.
 *
 * Android Things peripheral APIs are accessible through the PeripheralManager
 * For example, the snippet below will open a GPIO pin and set it to HIGH:
 *
 * val manager = PeripheralManager.getInstance()
 * val gpio = manager.openGpio("BCM6").apply {
 *     setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW)
 * }
 * gpio.value = true
 *
 * You can find additional examples on GitHub: https://github.com/androidthings
 */
class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wv_content.webViewClient = WebViewClient()
        val wvSetting = wv_content.settings
        wvSetting.apply {
            this.javaScriptEnabled = true
            this.setSupportMultipleWindows(false)
            this.javaScriptEnabled = false
            this.loadWithOverviewMode = true
            this.useWideViewPort = true
            this.setSupportZoom(true)
            this.builtInZoomControls = true
            this.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
            this.cacheMode = WebSettings.LOAD_NO_CACHE
            this.domStorageEnabled = true
        }
        wv_content.loadUrl("https://google.com")

        //둥둥 떠있는 애니메이션
        FloatingAnimation(iv_icon).start()

        btn_exit.setOnClickListener {
            finish()
        }


    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

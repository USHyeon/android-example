package com.umssung.kioskmodeexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

class BootCompleteReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action

        if (Intent.ACTION_BOOT_COMPLETED == action) {
            startActivity(context)
        }
    }

    fun startActivity(context: Context?) {
        context?.let {context ->
            val intent = Intent(context, MainActivity::class.java)
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
                return
            }
        }
    }
}
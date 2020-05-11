package com.test.roundcornerprogressbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {

            withContext(Dispatchers.Main) {
                for(i in 0..100) {
                    progress.progress = i.toFloat()
                    delay(30)
                }
            }
        }

    }
}

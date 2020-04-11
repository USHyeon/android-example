package com.test.mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.mvvm.aac.CounterActivity
import com.test.mvvm.aac.VmShareActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            var intent = Intent(this, CounterActivity::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            var intent = Intent(this, VmShareActivity::class.java)
            startActivity(intent)
        }
    }


}

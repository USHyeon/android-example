package com.test.cacheexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_save_cache.setOnClickListener {
            saveCache(et_cache.text.toString())
        }

        btn_read_cache.setOnClickListener {
            tv_read_cache.text = loadCache()
        }

    }

    fun saveCache(data: String) {
        try {
            val file = File(cacheDir, "myCache")
            val outputStream = FileOutputStream(file)
            outputStream.write(data.toByteArray())
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun loadCache(): String {
        try {
            val file = File(cacheDir, "myCache")
            if (!file.exists()) file.createNewFile()
            val inputStream = FileInputStream(file)
            val s = Scanner(inputStream)
            var text = ""
            while (s.hasNext()) {
                text += s.nextLine()
            }
            inputStream.close()

            return text
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return ""

    }
}

package com.example.maskinfokotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maskinfokotlin.adapter.StoreAdapter
import com.example.maskinfokotlin.model.Store

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.mainRecyclerView)
        val storeAdapter = StoreAdapter()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = storeAdapter
        }

        // Dummy Test
        val items = listOf(
            Store("abc", "111", "2222", 33.33, 33.33, "약국", "plenty", "33", "22"),
            Store("abc", "111", "2222", 33.33, 33.33, "약국", "plenty", "33", "22"),
            Store("abc", "111", "2222", 33.33, 33.33, "약국", "plenty", "33", "22")
        )
        storeAdapter.updateItems(items)

    }
}
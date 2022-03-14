package com.example.maskinfokotlin

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maskinfokotlin.adapter.StoreAdapter
import com.example.maskinfokotlin.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val storeAdapter = StoreAdapter()

        recyclerView = findViewById(R.id.mainRecyclerView)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = storeAdapter
        }

        mainViewModel.apply {
            itemLiveData.observe(this@MainActivity) { stores ->
                if (stores != null) {
                    storeAdapter.updateItems(stores)
                }
            }

            loadingLiveData.observe(this@MainActivity) { isLoading ->
                // 로딩 상태에 따른 프로그래스바 표시
                findViewById<View>(R.id.progressBar).visibility = if (isLoading) View.VISIBLE else View.GONE

            }
        }

        mainViewModel.fetchStoreInfo()

    }
}
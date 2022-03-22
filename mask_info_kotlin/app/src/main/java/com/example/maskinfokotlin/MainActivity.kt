package com.example.maskinfokotlin

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maskinfokotlin.adapter.StoreAdapter
import com.example.maskinfokotlin.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    private val requestPermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { map ->
        if (map[Manifest.permission.ACCESS_FINE_LOCATION]!!
            || map[Manifest.permission.ACCESS_COARSE_LOCATION]!!
        ) {
            mainViewModel.fetchStoreInfo()
        }
    }

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
                storeAdapter.updateItems(stores)
            }

            loadingLiveData.observe(this@MainActivity) { isLoading ->
                // 로딩 상태에 따른 프로그래스바 표시
                findViewById<View>(R.id.progressBar).visibility = if (isLoading) View.VISIBLE else View.GONE

            }
        }

        // 권한 체크
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // 권한 요청
            requestPermission.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
            return
        } else {
            // 권한이 승인됨
            mainViewModel.fetchStoreInfo()
        }


    }
}
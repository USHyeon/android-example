package com.example.maskinfokotlin.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maskinfokotlin.model.Store
import com.example.maskinfokotlin.model.StoreInfo
import com.example.maskinfokotlin.repository.MaskService
import com.example.maskinfokotlin.util.LocationDistance
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val service: MaskService, private val fusedLocationClient: FusedLocationProviderClient) : ViewModel() {
    val itemLiveData = MutableLiveData<List<Store>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    @SuppressLint("MissingPermission")
    fun fetchStoreInfo() {
        // 로딩 시작
        loadingLiveData.postValue(true)

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            viewModelScope.launch {
//                val lat = 37.188078
//                val lng = 127.043002
//                val storeInfo: StoreInfo = service.fetchStoreInfo(lat, lng)
                val storeInfo: StoreInfo = service.fetchStoreInfo(location.latitude, location.longitude)
                val stores: List<Store> = storeInfo.stores
                    .filter { item -> item.remain_stat != null }
                    .filter { item -> !item.remain_stat.equals("empty") }

                for (store: Store in stores) {
//                    store.distance = LocationDistance.distance(lat, lng, store.lat, store.lng, "k")
                    store.distance = LocationDistance.distance(location.latitude, location.longitude, store.lat, store.lng, "k")
                }
                itemLiveData.value = stores

                // 로딩 끝
                loadingLiveData.postValue(false)
            }
        }.addOnFailureListener { exception ->
            // TODO: Error 처리
            loadingLiveData.postValue(false)
        }


    }
}
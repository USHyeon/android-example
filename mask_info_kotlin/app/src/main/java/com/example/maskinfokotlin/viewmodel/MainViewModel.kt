package com.example.maskinfokotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maskinfokotlin.model.Store
import com.example.maskinfokotlin.model.StoreInfo
import com.example.maskinfokotlin.repository.MaskService
import com.example.maskinfokotlin.util.LocationDistance
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    val itemLiveData = MutableLiveData<List<Store>?>()
    val loadingLiveData = MutableLiveData<Boolean>()

    private val service: MaskService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(MaskService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        service = retrofit.create(MaskService::class.java)
    }

    fun fetchStoreInfo() {
        // 로딩 시작
        loadingLiveData.postValue(true)

        viewModelScope.launch {
            val lat = 37.188078
            val lng = 127.043002
            val storeInfo: StoreInfo = service.fetchStoreInfo(lat, lng)
            val stores: List<Store>? = storeInfo.stores
                ?.filter { item -> item.remain_stat != null }
                ?.filter { item -> !item.remain_stat.equals("empty") }

            if (stores != null) {
                for (store: Store in stores) {
                    store.distance = LocationDistance.distance(lat, lng, store.lat, store.lng, "k")
                }
            }

            itemLiveData.postValue(stores)

            // 로딩 끝
            loadingLiveData.postValue(false)
        }
    }
}
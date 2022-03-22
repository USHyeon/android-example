package com.example.maskinfokotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maskinfokotlin.model.Store
import com.example.maskinfokotlin.model.StoreInfo
import com.example.maskinfokotlin.repository.MaskService
import com.example.maskinfokotlin.util.LocationDistance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val service: MaskService) : ViewModel() {
    val itemLiveData = MutableLiveData<List<Store>?>()
    val loadingLiveData = MutableLiveData<Boolean>()

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
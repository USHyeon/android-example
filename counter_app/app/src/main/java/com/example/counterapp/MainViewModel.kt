package com.example.counterapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MainViewModel(private val handle: SavedStateHandle) : ViewModel() {
    private var count = handle.get<Int>("count") ?: 0 // 강제 종료됐을때, 값 유지? //확인 안됨.
        set(value) {
            field = value
            countLiveData.value = value
            handle.set("count", value)
        }

    val countLiveData = MutableLiveData<Int>()

    fun countIncrease() {
        count++
    }

    fun countDecrease() {
        count--
    }
}
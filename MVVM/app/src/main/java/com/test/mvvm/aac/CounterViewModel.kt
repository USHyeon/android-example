package com.test.mvvm.aac

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel() : ViewModel() {

    var counter = MutableLiveData<Int>()

    init {
        counter.value = 0
    }

    fun increase() {
        counter.value = counter.value?.plus(1)
    }

    fun decrease() {
        counter.value = counter.value?.minus(1)
    }

}
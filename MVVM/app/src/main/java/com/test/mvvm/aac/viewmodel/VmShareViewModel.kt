package com.test.mvvm.aac.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VmShareViewModel: ViewModel() {
    var progress = MutableLiveData<Int>()
}
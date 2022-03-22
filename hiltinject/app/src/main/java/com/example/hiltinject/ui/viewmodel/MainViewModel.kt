package com.example.hiltinject.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hiltinject.data.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MyRepository) : ViewModel() {
    fun getRepositoryHash() = repository.hashCode().toString()
}
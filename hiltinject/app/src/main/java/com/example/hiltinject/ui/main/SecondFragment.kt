package com.example.hiltinject.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hiltinject.R
import com.example.hiltinject.data.MyRepository
import com.example.hiltinject.di.qualifier.ActivityHash
import com.example.hiltinject.di.qualifier.AppHash
import com.example.hiltinject.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SecondFragment : Fragment(R.layout.fragment_second) {
    private val activityViewModel by activityViewModels<MainViewModel>()
    private val viewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var repository: MyRepository

    @AppHash
    @Inject
    lateinit var applicationHash: String

    @ActivityHash
    @Inject
    lateinit var activityHash: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBack = view.findViewById<Button>(R.id.btn_back)
        btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_mainFragment)
        }
        Log.d("SecondFragment", "${repository.hashCode()}")
        Log.d("SecondFragment", "appHash: $applicationHash")
        Log.d("SecondFragment", "ActivityHash: $activityHash")
        Log.d("SecondFragment", "ViewModel: ${viewModel.getRepositoryHash()}")
        Log.d("MainFragment", "ActivityViewModel: ${activityViewModel.getRepositoryHash()}")
    }
}
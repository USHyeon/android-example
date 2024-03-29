package com.example.hiltinject.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hiltinject.R
import com.example.hiltinject.data.MyRepository
import com.example.hiltinject.di.qualifier.ActivityHash
import com.example.hiltinject.di.qualifier.AppHash
import com.example.hiltinject.ui.second.SecondActivity
import com.example.hiltinject.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
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

        val btnActivity = view.findViewById<Button>(R.id.btn_activity)
        btnActivity.setOnClickListener {
            val intent = Intent(requireContext(), SecondActivity::class.java)
            startActivity(intent)
        }
        val btnFragment = view.findViewById<Button>(R.id.btn_fragment)
        btnFragment.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_secondFragment)
        }

        Log.d("MainFragment", "${repository.hashCode()}")
        Log.d("MainFragment", "appHash: $applicationHash")
        Log.d("MainFragment", "ActivityHash: $activityHash")
        Log.d("MainFragment", "ViewModel: ${viewModel.getRepositoryHash()}")
        Log.d("MainFragment", "ActivityViewModel: ${activityViewModel.getRepositoryHash()}")
    }

}
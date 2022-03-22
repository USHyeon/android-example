package com.example.hiltinject.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.hiltinject.R
import com.example.hiltinject.ui.data.MyRepository
import com.example.hiltinject.ui.second.SecondActivity

class MainFragment : Fragment(R.layout.fragment_main) {

    val repository = MyRepository()

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
    }

}
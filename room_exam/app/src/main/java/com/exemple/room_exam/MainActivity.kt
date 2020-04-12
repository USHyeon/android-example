package com.exemple.room_exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.exemple.room_exam.databinding.ActivityMainBinding
import com.exemple.room_exam.models.Todo
import com.exemple.room_exam.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        val mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.viewModel = mainViewModel

        btn_insert.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                // Dispatchers.IO : Background Thread
                mainViewModel.insert(Todo(edittext.text.toString()))
            }
        }

        btn_delete.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                // Dispatchers.IO : Background Thread
                mainViewModel.delete(Todo(edittext.text.toString()))
            }
        }

    }


}

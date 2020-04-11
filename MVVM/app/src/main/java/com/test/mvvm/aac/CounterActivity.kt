package com.test.mvvm.aac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.test.mvvm.R
import com.test.mvvm.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = DataBindingUtil.setContentView<ActivityCounterBinding>(this, R.layout.activity_counter)

        binding.lifecycleOwner = this

        var viewModel = ViewModelProvider(this).get(CounterViewModel::class.java)

        binding.viewModel = viewModel

    }
}

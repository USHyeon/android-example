package com.example.presentation.view

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.utils.ErrorType
import com.example.domain.utils.ScreenState
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentManNameBinding
import com.example.presentation.viewmodel.MainViewModel

class ManNameFragment : BaseFragment<FragmentManNameBinding>(R.layout.fragment_man_name) {
    private val mainViewModel by activityViewModels<MainViewModel>()
    override fun init() {
        binding.fragment = this
        observeViewModel()
    }

    fun resultBtnClick(view: View) {
        mainViewModel.manNameResult = binding.etName.text.toString()
        mainViewModel.checkLoveCalculator("love-calculator.p.rapidapi.com", "e7d5efd730msh249c5d152c3900dp1faf4djsn59c08d71c3bb", mainViewModel.manNameResult, mainViewModel.womanNameResult)
    }

    private fun observeViewModel() {
        mainViewModel.apiCallEvent.observe(this) {
            when (it) {
                ScreenState.LOADING -> {
                    binding.progressLoading.visibility = View.VISIBLE
                }
                ScreenState.SUCCESS -> {
                    binding.progressLoading.visibility = View.INVISIBLE
                    this.findNavController().navigate(R.id.action_manNameFragment_to_resultFragment)
                }
                ScreenState.ERROR -> {
                    binding.progressLoading.visibility = View.INVISIBLE
                    toastErrorMsg()
                }
                else -> {
                    binding.progressLoading.visibility = View.INVISIBLE
                    shortShowToast("알 수 없는 오류가 발생했습니다.")
                }

            }
        }
    }

    private fun toastErrorMsg() {
        when (mainViewModel.apiErrorType) {
            ErrorType.NETWORK -> longShowToast("네트워크 오류가 발생했습니다.")
            ErrorType.SESSION_EXPIRED -> longShowToast("세션이 만료되었습니다.")
            ErrorType.TIMEOUT -> longShowToast("호출 시간이 초과되었습니다.")
            ErrorType.UNKNOWN -> longShowToast("예기치 못한 오류가 발생했습니다.")
        }
    }
}
package com.umssung.animationtestexample.ui.home

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.umssung.animationtestexample.R
import com.umssung.animationtestexample.ui.animation.FloatingAnimation
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        homeViewModel.img.observe(viewLifecycleOwner, Observer {
            iv_icon.setImageResource(it)
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //둥둥 떠있는 애니메이션
        FloatingAnimation(img_logo).start()

        alphaAni()

        val set: AnimatorSet =
            AnimatorInflater.loadAnimator(context, R.animator.animation_test).apply {
                setTarget(iv_icon)
                start()
            } as AnimatorSet
    }


    private fun alphaAni() {
        btn_alpha_on.setOnClickListener {
            showOff()
        }



//        btn_alpha_off.setOnClickListener {
//            iv_icon.animate().alpha(0f).setDuration(duration).withEndAction {
//                iv_icon.alpha = 1f
//            }.start()
//        }
    }

    private  fun showOff() {
      iv_icon.animate().alpha(0f).setDuration(3000).withStartAction {
            homeViewModel._text.value = "animation start"
        }.withEndAction {
          showOn()
        }.start()
    }

    private fun showOn() {
        iv_icon.animate().alpha(1f).setDuration(3000).withStartAction {
            homeViewModel._text.value = "animation start"
        }.withEndAction {
            showOff()
        }.start()
    }
}
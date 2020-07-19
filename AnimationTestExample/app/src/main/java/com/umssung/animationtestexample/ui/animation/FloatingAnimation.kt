package com.umssung.animationtestexample.ui.animation

import android.widget.ImageView

class FloatingAnimation {

    private var imageView: ImageView? = null

    constructor(imageView: ImageView) {
        this.imageView = imageView
    }

    fun start() {
        floatingUp()
    }

    private fun floatingUp() {
        imageView?.animate()?.translationY(10f)?.withEndAction {
            floatingOff()
        }?.start()
    }

    private fun floatingOff() {
        imageView?.animate()?.translationY(0f)?.withEndAction {
            floatingUp()
        }?.start()
    }
}
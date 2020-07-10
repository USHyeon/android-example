package kr.co.spacetalk.benchmanager.ui.commons

import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class AnimationSetGeneral<T>(view: T) {
    private var view = view

    var startDelayStart: Long = 0
    var startDelayEnd: Long = 0

    var alphaStart: Float = 1f
    var alphaEnd: Float = 1f
    var durationStart: Long = 1000
    var durationEnd: Long = 1000

    var translationXStart: Float = 0f
    var translationXEnd: Float = 0f
    var translationYStart: Float = 0f
    var translationYEnd: Float = 0f

    var rotationStart: Float = 0f
    var rotationEnd: Float = 0f
    var rotationYStart: Float = 0f
    var rotationYEnd: Float = 0f
    var rotationXStart: Float = 0f
    var rotationXEnd: Float = 0f

    var scaleXStart: Float = 1f
    var scaleXEnd: Float = 1f
    var scaleYStart: Float = 1f
    var scaleYEnd: Float = 1f

    var endActionStart =
        Action.NONE
    var endActionEnd =
        Action.NONE
    var firstStart =
        Action.START

    enum class Action {
        END, START, NONE
    }

    fun cancle() {
        when (view) {
            is ImageView -> (view as ImageView).animate()?.cancel()
            is TextView-> (view as TextView).animate()?.cancel()
            is Button -> (view as Button).animate()?.cancel()
            is LinearLayout -> (view as LinearLayout).animate()?.cancel()
        }

    }

    fun start() {
        when (firstStart) {
            Action.END -> animationEnd()
            Action.START -> animationStart()
            else -> animationStart()
        }
    }

    private fun animationStart() {
        when (view) {
            is ImageView -> {
                (view as ImageView).animate()?.apply {
                    this.startDelay = startDelayStart
                    this.alpha(alphaStart)
                    this.duration = durationStart
                    this.translationX(translationXStart)
                    this.translationY(translationYStart)
                    this.rotation(rotationStart)
                    this.rotationX(rotationXStart)
                    this.rotationY(rotationYStart)
                    this.scaleX(scaleXStart)
                    this.scaleY(scaleYStart)
                    this.withStartAction {

                    }
                    this.withEndAction {
                        when (endActionStart) {
                            Action.END -> animationEnd()
                            else -> return@withEndAction
                        }
                    }
                    this.start()
                }
            }
            is TextView -> {
                (view as TextView).animate()?.apply {
                    this.startDelay = startDelayStart
                    this.alpha(alphaStart)
                    this.duration = durationStart
                    this.translationX(translationXStart)
                    this.translationY(translationYStart)
                    this.rotation(rotationStart)
                    this.rotationX(rotationXStart)
                    this.rotationY(rotationYStart)
                    this.scaleX(scaleXStart)
                    this.scaleY(scaleYStart)
                    this.withStartAction {

                    }
                    this.withEndAction {
                        when (endActionStart) {
                            Action.END -> animationEnd()
                            else -> return@withEndAction
                        }
                    }
                    this.start()
                }
            }
            is Button -> {
                (view as Button).animate()?.apply {
                    this.startDelay = startDelayStart
                    this.alpha(alphaStart)
                    this.duration = durationStart
                    this.translationX(translationXStart)
                    this.translationY(translationYStart)
                    this.rotation(rotationStart)
                    this.rotationX(rotationXStart)
                    this.rotationY(rotationYStart)
                    this.scaleX(scaleXStart)
                    this.scaleY(scaleYStart)
                    this.withStartAction {

                    }
                    this.withEndAction {
                        when (endActionStart) {
                            Action.END -> animationEnd()
                            else -> return@withEndAction
                        }
                    }
                    this.start()
                }
            }
            is LinearLayout -> {
                (view as LinearLayout).animate()?.apply {
                    this.startDelay = startDelayStart
                    this.alpha(alphaStart)
                    this.duration = durationStart
                    this.translationX(translationXStart)
                    this.translationY(translationYStart)
                    this.rotation(rotationStart)
                    this.rotationX(rotationXStart)
                    this.rotationY(rotationYStart)
                    this.scaleX(scaleXStart)
                    this.scaleY(scaleYStart)
                    this.withStartAction {

                    }
                    this.withEndAction {
                        when (endActionStart) {
                            Action.END -> animationEnd()
                            else -> return@withEndAction
                        }
                    }
                    this.start()
                }
            }
        }
    }

    private fun animationEnd() {
        when (view) {
            is ImageView -> {
                (view as ImageView).animate()?.apply {
                    this.startDelay = startDelayEnd
                    this.alpha(alphaEnd)
                    this.duration = durationEnd
                    this.translationX(translationXEnd)
                    this.translationY(translationYEnd)
                    this.rotation(rotationEnd)
                    this.rotationX(rotationXEnd)
                    this.rotationY(rotationYEnd)
                    this.scaleX(scaleXEnd)
                    this.scaleY(scaleYEnd)
                    this.withStartAction {
                    }
                    this.withEndAction {
                        when (endActionEnd) {
                            Action.START -> animationStart()
                            else -> return@withEndAction
                        }
                    }
                    this.start()
                }
            }
            is TextView -> {
                (view as TextView).animate()?.apply {
                    this.startDelay = startDelayEnd
                    this.alpha(alphaEnd)
                    this.duration = durationEnd
                    this.translationX(translationXEnd)
                    this.translationY(translationYEnd)
                    this.rotation(rotationEnd)
                    this.rotationX(rotationXEnd)
                    this.rotationY(rotationYEnd)
                    this.scaleX(scaleXEnd)
                    this.scaleY(scaleYEnd)
                    this.withStartAction {
                    }
                    this.withEndAction {
                        when (endActionEnd) {
                            Action.START -> animationStart()
                            else -> return@withEndAction
                        }
                    }
                    this.start()
                }
            }
            is Button -> {
                (view as Button).animate()?.apply {
                    this.startDelay = startDelayEnd
                    this.alpha(alphaEnd)
                    this.duration = durationEnd
                    this.translationX(translationXEnd)
                    this.translationY(translationYEnd)
                    this.rotation(rotationEnd)
                    this.rotationX(rotationXEnd)
                    this.rotationY(rotationYEnd)
                    this.scaleX(scaleXEnd)
                    this.scaleY(scaleYEnd)
                    this.withStartAction {
                    }
                    this.withEndAction {
                        when (endActionEnd) {
                            Action.START -> animationStart()
                            else -> return@withEndAction
                        }
                    }
                    this.start()
                }
            }
            is LinearLayout -> {
                (view as LinearLayout).animate()?.apply {
                    this.startDelay = startDelayEnd
                    this.alpha(alphaEnd)
                    this.duration = durationEnd
                    this.translationX(translationXEnd)
                    this.translationY(translationYEnd)
                    this.rotation(rotationEnd)
                    this.rotationX(rotationXEnd)
                    this.rotationY(rotationYEnd)
                    this.scaleX(scaleXEnd)
                    this.scaleY(scaleYEnd)
                    this.withStartAction {
                    }
                    this.withEndAction {
                        when (endActionEnd) {
                            Action.START -> animationStart()
                            else -> return@withEndAction
                        }
                    }
                    this.start()
                }
            }
        }
    }
}
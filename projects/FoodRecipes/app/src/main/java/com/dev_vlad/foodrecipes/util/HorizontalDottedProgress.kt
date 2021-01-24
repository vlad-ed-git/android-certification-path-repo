package com.dev_vlad.foodrecipes.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation

import android.view.animation.LinearInterpolator
import android.view.animation.Transformation

/*
thanks @Naveen Shriyan -- https://stackoverflow.com/questions/37741872/how-to-make-custom-dotted-progress-bar-in-android
*** draws 10 dots. then loops through drawing 1 dot at a time
*  each time now it makes it larger
*  this is done very fast
 */
class HorizontalDottedProgress : View {
    //actual dot radius
    private val mDotRadius : Float = 5f

    //Bounced Dot Radius
    private val mBounceDotRadius : Float = 8f

    //to get identified in which position dot has to bounce
    private var mDotPosition = 0

    //specify how many dots you need in a progressbar
    private val mDotAmount = 10

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

    //Method to draw your customized dot on the canvas

    private val paint = Paint()
    protected override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //set the color for the dot that you want to draw
        paint.color = Color.parseColor("#3700B3")

        //function to create dot
        createDot(canvas, paint)
    }

    protected override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        //Animation called when attaching to the window, i.e to your screen
        startAnimation()
    }

    private fun createDot(canvas: Canvas, paint: Paint) {

        //here i have set progress bar with 10 dots , so repeat and wnen i = mDotPosition  then increase the radius of dot i.e mBounceDotRadius
        for (i in 0 until mDotAmount) {
            if (i == mDotPosition) {
                canvas.drawCircle((10 + i * 20).toFloat(), mBounceDotRadius, mBounceDotRadius, paint)
            } else {
                canvas.drawCircle((10 + i * 20).toFloat(), mBounceDotRadius, mDotRadius, paint)
            }
        }
    }

    protected override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width: Int

        //calculate the view width
        val calculatedWidth = 20 * 9
        width = calculatedWidth
        val height: Int = (mBounceDotRadius * 2).toInt()


        //MUST CALL THIS
        setMeasuredDimension(width, height)
    }

    private fun startAnimation() {
        val bounceAnimation: BounceAnimation = BounceAnimation()
        bounceAnimation.duration = 100
        bounceAnimation.repeatCount = Animation.INFINITE
        bounceAnimation.interpolator = LinearInterpolator()
        bounceAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {}
            override fun onAnimationRepeat(animation: Animation) {
                mDotPosition++
                //when mDotPosition == mDotAmount , then start again applying animation from 0th positon , i.e  mDotPosition = 0;
                if (mDotPosition == mDotAmount) {
                    mDotPosition = 0
                }

            }
        })
        startAnimation(bounceAnimation)
    }

    private inner class BounceAnimation : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            super.applyTransformation(interpolatedTime, t)
            //call invalidate to redraw your view againg.
            invalidate()
        }
    }
}
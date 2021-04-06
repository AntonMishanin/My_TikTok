package com.example.feature_splash

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View


class MyView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var mLastAngle = 0
    private val mPivotX = 0
    private var mPivotY: Int = 0

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
         rotate(canvas)
        invalidate()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        f(event)
        invalidate()
        Log.d("TAG", "viewRotation = $viewRotation")
        return true
    }

    private fun rotate(canvas: Canvas?) {
        canvas?.save()
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.icon)
        canvas?.drawBitmap(bitmap, 0f, 0f, null)
        //canvas?.rotate((180 / Math.PI).toFloat())
        canvas?.rotate(viewRotation)
        canvas?.restore()
    }
/*
    private fun doRotationEvent(event: MotionEvent): Boolean {
        //Calculate the angle between the two fingers
        val deltaX = event.getX(0) - event.getX(1)
        val deltaY = event.getY(0) - event.getY(1)
        val radians = Math.atan((deltaY / deltaX).toDouble())
        //Convert to degrees
        val degrees = (radians * 180 / Math.PI).toInt()
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN, MotionEvent.ACTION_POINTER_UP ->                 //Mark the initial angle
                mLastAngle = degrees
            MotionEvent.ACTION_MOVE -> {
                // ATAN returns a converted value between -90deg and +90deg
                // which creates a point when two fingers are vertical where the
                // angle flips sign.  We handle this case by rotating a small amount
                // (5 degrees) in the direction we were traveling
                if (degrees - mLastAngle > 45) {
                    //Going CCW across the boundary
                    mImageMatrix.postRotate(-5, mPivotX, mPivotY)
                } else if (degrees - mLastAngle < -45) {
                    //Going CW across the boundary
                    mImageMatrix.postRotate(5, mPivotX, mPivotY)
                } else {
                    //Normal rotation, rotate the difference
                    mImageMatrix.postRotate(degrees - mLastAngle, mPivotX, mPivotY)
                }
                //Post the rotation to the image
                setImageMatrix(mImageMatrix)
                //Save the current angle
                mLastAngle = degrees
            }
        }
        return true
    }

 */

    var viewRotation = 0f
    var fingerRotation = 0.0
    var newFingerRotation = 0.0

    fun f(event: MotionEvent?) {
        val x = event?.getX() ?: 0f
        val y = event?.getY() ?: 0f

        val xc = this.getWidth() / 2f
        val yc = this.getHeight() / 2f

        when (event?.getAction()) {
            MotionEvent.ACTION_DOWN -> {
                viewRotation = rotation
                fingerRotation =
                    Math.toDegrees(Math.atan2((x - xc).toDouble(), (yc - y).toDouble()))
            }
            MotionEvent.ACTION_MOVE -> {
                newFingerRotation =
                    Math.toDegrees(Math.atan2((x - xc).toDouble(), (yc - y).toDouble()))
                viewRotation = (viewRotation + newFingerRotation - fingerRotation).toFloat()
            }
            MotionEvent.ACTION_UP -> {
                newFingerRotation = 0.0
                fingerRotation = newFingerRotation
            }
        }
    }
}
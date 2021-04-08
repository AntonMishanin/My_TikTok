package com.example.feature_splash;

import android.util.Log;
import android.view.MotionEvent;

public class RotationGestureDetector {

    private float mAngle;
    private final float centerX;
    private final float centerY;

    private float minValue;
    private float maxValue;
    private float currentValue = 0;

    private final OnRotationGestureListener mListener;

    public float getAngle() {
        return mAngle;
    }

    public float getCurrentValue() {
        return currentValue;
    }

    public RotationGestureDetector(OnRotationGestureListener listener, float centerX, float centerY, float minValue, float maxValue) {
        mListener = listener;
        this.centerX = centerX;
        this.centerY = centerY;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public boolean onTouchEvent(MotionEvent event) {
        float sX = event.getX();
        float sY = event.getY();

        if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
            mAngle = angleBetweenLines2(centerX, centerY, sX, sY);
            if (mListener != null) {
                mListener.OnRotation(this);
            }
        }
        return true;
    }

    private float angleBetweenLines2(float fX, float fY, float sX, float sY) {
        float angle1 = (float) Math.atan2((fY - sY), (fX - sX));

        float angle = ((float) Math.toDegrees(angle1)) % 360;
        if (angle < -180.f) angle += 360.0f;
        if (angle > 180.f) angle -= 360.0f;

        if (minValue < 0) {
            minValue *= -1;
        }
        if (maxValue < 0) {
            maxValue *= -1;
        }
        float sum = minValue + maxValue;

        angle += 180;

        currentValue = angle * (sum / 360) - minValue;
        Log.d("TAG", "angle = " + angle);
        Log.d("TAG", "currentValue = " + currentValue);
        Log.d("TAG", "sum = " + sum);
        return angle;
    }

    public interface OnRotationGestureListener {
        void OnRotation(RotationGestureDetector rotationDetector);
    }
}
package com.example.feature_splash.presentation

import android.annotation.SuppressLint
import android.graphics.Matrix
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.feature_splash.R
import com.example.feature_splash.RotationGestureDetector
import com.example.feature_splash.navigation.SplashNavigator
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class SplashFragment : Fragment(R.layout.fragment_splash),
    RotationGestureDetector.OnRotationGestureListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: SplashViewModel by viewModels { viewModelFactory }

    private var mRotationDetector: RotationGestureDetector? = null

    private var imageView: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onStart() {
        super.onStart()

        val navigator = requireActivity() as SplashNavigator
        viewModel.loadToken(navigator)

        imageView = view?.findViewById(R.id.rotated_image)

        mRotationDetector = RotationGestureDetector(
            this,
            (imageView?.drawable?.intrinsicWidth ?: 0) / 2f,
            (imageView?.drawable?.intrinsicHeight ?: 0) / 2f,
            0f,
            1.5f
        )

        imageView?.setOnTouchListener { _, event ->
            mRotationDetector?.onTouchEvent(event) ?: true
        }
    }

    override fun OnRotation(rotationDetector: RotationGestureDetector?) {
        val angle = rotationDetector!!.angle

        val matrix = Matrix()
        matrix.postRotate(
            angle,
            (imageView?.drawable?.intrinsicWidth ?: 0) / 2f,
            (imageView?.drawable?.intrinsicHeight ?: 0) / 2f
        )
        val rotatedValues = FloatArray(9)
        matrix.getValues(rotatedValues)

        val sourceMatrix = imageView?.matrix
        sourceMatrix?.setValues(rotatedValues)
        imageView?.imageMatrix = sourceMatrix
    }
}
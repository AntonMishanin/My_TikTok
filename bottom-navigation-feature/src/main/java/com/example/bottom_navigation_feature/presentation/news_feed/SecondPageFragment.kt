package com.example.bottom_navigation_feature.presentation.news_feed

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.bottom_navigation_feature.R
import com.google.android.material.snackbar.Snackbar

class SecondPageFragment : Fragment(R.layout.fragment_second) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Snackbar.make(requireView(), "Label", Snackbar.LENGTH_LONG)
            .setAction("Action") {
                Log.d("TAG", "On Snackbar click")
            }
            .show()
    }
}
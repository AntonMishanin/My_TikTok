package com.example.feature_users_feed.items.feature_three

import android.util.Log
import androidx.fragment.app.Fragment
import com.example.feature_users_feed.R

class FeatureThreeFragment : Fragment(R.layout.fragment_feature_three) {

    override fun onStart() {
        super.onStart()
        Log.d("TAG", "FeatureThreeFragment")
    }
}
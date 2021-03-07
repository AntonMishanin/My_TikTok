package com.example.feature_users_feed.items.feature_one

import android.util.Log
import androidx.fragment.app.Fragment
import com.example.feature_users_feed.R

class FeatureOneFragment : Fragment(R.layout.fragment_feature_one) {

    override fun onStart() {
        super.onStart()
        Log.d("TAG", "FeatureOneFragment")
    }
}
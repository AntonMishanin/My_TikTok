package com.example.feature_users_feed.items.feature_two

import android.util.Log
import androidx.fragment.app.Fragment
import com.example.feature_users_feed.R

class FeatureTwoFragment : Fragment(R.layout.fragment_feature_two) {

    override fun onStart() {
        super.onStart()
        Log.d("TAG", "FeatureTwoFragment")
    }
}
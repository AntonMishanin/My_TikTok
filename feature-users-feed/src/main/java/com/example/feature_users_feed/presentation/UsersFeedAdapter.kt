package com.example.feature_users_feed.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_users_feed.R
import com.example.feature_users_feed.databinding.ItemNewsFeedBinding
import com.example.feature_users_feed.domain.entity.ContentEntity
import com.example.feature_users_feed.items.feature_four.FeatureFourFragment
import com.example.feature_users_feed.items.feature_one.FeatureOneFragment
import com.example.feature_users_feed.items.feature_three.FeatureThreeFragment
import com.example.feature_users_feed.items.feature_two.FeatureTwoFragment
import com.example.feature_users_feed.utils.Constants.Companion.VIEW_TYPE_FEATURE_FOUR
import com.example.feature_users_feed.utils.Constants.Companion.VIEW_TYPE_FEATURE_ONE
import com.example.feature_users_feed.utils.Constants.Companion.VIEW_TYPE_FEATURE_THREE
import com.example.feature_users_feed.utils.Constants.Companion.VIEW_TYPE_FEATURE_TWO


class UsersFeedAdapter(
    private val supportFragmentManager: FragmentManager
) : RecyclerView.Adapter<UsersFeedAdapter.NewsFeedViewHolder>() {

    var content: List<ContentEntity> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsFeedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsFeedBinding.inflate(inflater, parent, false)
        return NewsFeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsFeedViewHolder, position: Int) {
        val fragment: Fragment = when (holder.itemViewType) {
            VIEW_TYPE_FEATURE_ONE -> FeatureOneFragment()
            VIEW_TYPE_FEATURE_TWO -> FeatureTwoFragment()
            VIEW_TYPE_FEATURE_THREE -> FeatureThreeFragment()
            VIEW_TYPE_FEATURE_FOUR -> FeatureFourFragment()
            else -> FeatureOneFragment()
        }

        supportFragmentManager.commit {
            val containerId = View.generateViewId()
            holder.fragmentContainer.id = containerId
            add(containerId, fragment)
        }
    }

    override fun getItemViewType(position: Int): Int = content[position].viewType

    override fun getItemCount(): Int = content.size

    class NewsFeedViewHolder(binding: ItemNewsFeedBinding) : RecyclerView.ViewHolder(binding.root) {

        val fragmentContainer: FragmentContainerView =
            itemView.findViewById(R.id.fragment_container_view)
    }
}
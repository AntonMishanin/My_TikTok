package com.example.feature_users_feed.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_users_feed.R
import com.example.feature_users_feed.domain.entity.ContentEntity
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class UsersFeedFragment : Fragment(R.layout.fragment_news_feed) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: UsersFeedViewModel by viewModels { viewModelFactory }

    private lateinit var adapter: UsersFeedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        viewModel.loadContent()
        initView()
        observeViewModel()
    }

    private fun initView() {
        adapter = UsersFeedAdapter(requireActivity().supportFragmentManager)

        val usersFeedRecyclerView = view?.findViewById<RecyclerView>(R.id.recycler_view_users_feed)
        usersFeedRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
        usersFeedRecyclerView?.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.content.observe(viewLifecycleOwner, ::onChangeContent)
    }

    private fun onChangeContent(content: List<ContentEntity>) {
        adapter.content = content
    }
}
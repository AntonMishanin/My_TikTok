package com.example.feature_users_feed.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_users_feed.R
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class NewsFeedFragment : Fragment(R.layout.fragment_news_feed) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: NewsFeedViewModel by viewModels { viewModelFactory }

    private lateinit var adapter: NewsAdapter

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
        adapter = NewsAdapter {

        }

        val recyclerViewOrderDetail =
            view?.findViewById<RecyclerView>(R.id.recycler_view_users_feed)
        recyclerViewOrderDetail?.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewOrderDetail?.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.content.observe(viewLifecycleOwner, adapter::setListCurrency)
    }
}
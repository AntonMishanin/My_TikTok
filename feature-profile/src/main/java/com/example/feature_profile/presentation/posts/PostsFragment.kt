package com.example.feature_profile.presentation.posts

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.shared_base.UiState
import com.example.feature_profile.R
import com.example.shared_utils.visible
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PostsFragment : Fragment(R.layout.fragment_posts) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: PostsViewModel by viewModels { viewModelFactory }

    private var recyclerViewPosts: RecyclerView? = null
    private var progress: ProgressBar? = null
    private var failLayout: View? = null
    private var emptyContentLayout: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        viewModel.onViewCreated()
        observeViewModel()
    }

    private fun initView() {
        recyclerViewPosts = view?.findViewById(R.id.recyclerView_posts)
        progress = view?.findViewById(R.id.progressBar_post)
        failLayout = view?.findViewById(R.id.layout_fail)
        emptyContentLayout = view?.findViewById(R.id.layout_empty_content)
    }

    private fun observeViewModel() {
        viewModel.state.observe(this::getLifecycle, ::onChangeState)
    }

    private fun onChangeState(state: UiState) {
        recyclerViewPosts?.visible(state == UiState.CONTENT)
        progress?.visible(state == UiState.PROGRESS)
        failLayout?.visible(state == UiState.FAIL)
        emptyContentLayout?.visible(state == UiState.EMPTY_CONTENT)
    }
}
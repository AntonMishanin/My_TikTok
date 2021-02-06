package com.example.bottom_navigation_feature.presentation.news_feed

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottom_navigation_feature.R
import com.example.bottom_navigation_feature.navigator.BottomNavigator
import com.google.android.material.textfield.TextInputLayout

class FirstPageFragment : Fragment(R.layout.fragment_first) {

    private var newsAdapter: NewsAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val textFieldMenu = view?.findViewById<TextInputLayout>(R.id.textField_menu)

        val items = listOf("Material", "Design", "Components", "Android")
        val adapter = ArrayAdapter(requireContext(), R.layout.item_menu, items)
        (textFieldMenu?.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        //RecyclerView
        newsAdapter = NewsAdapter {
            val navigator = requireActivity() as BottomNavigator
            navigator.navigateToDetailFragment()
        }

        val recyclerViewOrderDetail =
            view?.findViewById<RecyclerView>(R.id.recyclerView_news)
        recyclerViewOrderDetail?.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewOrderDetail?.adapter = newsAdapter


        val list = ArrayList<String>()
        list.add("111111")
        list.add("111111")
        list.add("111111")
        list.add("111111")
        list.add("111111")
        list.add("111111")
        newsAdapter?.setListCurrency(list)
    }
}
package com.example.bottom_navigation_feature.presentation.news_feed

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import com.example.bottom_navigation_feature.R
import com.google.android.material.textfield.TextInputLayout

class FirstPageFragment : Fragment(R.layout.fragment_first) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val textFieldMenu = view?.findViewById<TextInputLayout>(R.id.textField_menu)

        val items = listOf("Material", "Design", "Components", "Android")
        val adapter = ArrayAdapter(requireContext(), R.layout.item_menu, items)
        (textFieldMenu?.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }
}
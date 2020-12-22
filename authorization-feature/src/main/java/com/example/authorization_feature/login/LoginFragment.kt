package com.example.authorization_feature.login

import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.authorization_feature.AuthorizationNavigator
import com.example.authorization_feature.R
import com.example.data.RepositoryImpl
import com.example.data.preferences.SharedPref

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val navigator = requireActivity() as AuthorizationNavigator

        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val sharedPref = SharedPref(sharedPreferences)
        val repository = RepositoryImpl(sharedPref)

        viewModel.onViewCreated(repository, navigator)

        val loginButton = view?.findViewById<Button>(R.id.button_login)
        loginButton?.setOnClickListener {
            viewModel.onClickLogin()
        }

        val registrationButton = view?.findViewById<Button>(R.id.button_go_to_registration)
        registrationButton?.setOnClickListener {
            viewModel.onClickRegistration()
        }
    }
}
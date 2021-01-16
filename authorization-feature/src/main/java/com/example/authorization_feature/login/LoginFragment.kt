package com.example.authorization_feature.login

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.authorization_feature.AuthorizationNavigator
import com.example.authorization_feature.R
import com.example.data.RepositoryImpl
import com.example.data.database.UserDatabase
import com.example.data.preferences.SharedPref

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val navigator = requireActivity() as AuthorizationNavigator

        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val sharedPref = SharedPref(sharedPreferences)

        val userDatabase =
            UserDatabase.getUserDatabase(requireContext().applicationContext)
        val userDao = userDatabase.getUserDao()

        val repository = RepositoryImpl(sharedPref, userDao)

        viewModel.onViewCreated(repository, navigator)
        initListeners()
    }

    override fun onDestroyView() {
        viewModel.onDestroyView()
        super.onDestroyView()
    }

    private fun initListeners(){
        val userName = requireView().findViewById<EditText>(R.id.editText_user_name_login)
        userName.addTextChangedListener{
            viewModel.enteringUserName(it)
        }

        val password = requireView().findViewById<EditText>(R.id.editText_user_password_login)
        password.addTextChangedListener {
            viewModel.enteringPassword(it)
        }

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
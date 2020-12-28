package com.example.authorization_feature.registration

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.fragment.app.Fragment
import com.example.authorization_feature.AuthorizationNavigator
import com.example.authorization_feature.R
import com.example.data.RepositoryImpl
import com.example.data.database.UserDatabase
import com.example.data.preferences.SharedPref

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val viewModel: RegistrationViewModel by viewModels()

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

        val loginButton = view?.findViewById<Button>(R.id.button_go_to_login)
        loginButton?.setOnClickListener {
            viewModel.onClickLogin()
        }

        val registrationButton = view?.findViewById<Button>(R.id.button_registration)
        registrationButton?.setOnClickListener {
            viewModel.onClickRegistration()
        }

        val inputUserName = requireView().findViewById<EditText>(R.id.editText_input_user_name)
        inputUserName.addTextChangedListener {
            viewModel.userNameChanged(it)
        }

        val inputPassword = requireView().findViewById<EditText>(R.id.editText_input_user_password)
        inputPassword.addTextChangedListener {
            viewModel.passwordChanged(it)
        }
    }
}
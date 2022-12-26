package com.example.flagquiz.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.flagquiz.R
import com.example.flagquiz.databinding.FragmentSignInScreenBinding
import com.example.flagquiz.utils.alerter
import com.example.flagquiz.viewmodels.SignInViewModel
import com.example.flagquiz.viewmodels.impl.SignInViewModelImpl


class SignInScreen : Fragment(R.layout.fragment_sign_in_screen) {
    private val viewModel: SignInViewModel by viewModels<SignInViewModelImpl>()
    private val binding: FragmentSignInScreenBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openMainScreenLiveData.observe(this){
            findNavController().navigate(SignInScreenDirections.actionSignInScreenToMainScreen(it))
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnLogin.setOnClickListener {
            if (binding.editPassword.text.toString().isNotEmpty())
                viewModel.login(binding.editEmail.text.toString(),binding.editPassword.text.toString())
            else binding.editPassword.error="Password is empty"
        }
        viewModel.errorMessageLiveData.observe(viewLifecycleOwner){
            when(it.first){
                1-> binding.editEmail.error=it.second
                2-> binding.editPassword.error=it.second
            }
        }
        viewModel.messageLiveData.observe(viewLifecycleOwner){
            alerter(it)
        }

    }
}
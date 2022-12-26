package com.example.flagquiz.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.flagquiz.R
import com.example.flagquiz.viewmodels.impl.SplashViewModel

class SplashScreen : Fragment(R.layout.fragment_splash_screen) {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openSignInLiveData.observe(this){
            findNavController().navigate(SplashScreenDirections.actionSplashScreenToSignInScreen())
        }
        viewModel.openMainScreenLiveData.observe(this){
            findNavController().navigate(SplashScreenDirections.actionSplashScreenToMainScreen(it))
        }
    }

}
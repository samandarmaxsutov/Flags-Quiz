package com.example.flagquiz.ui.screens


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.flagquiz.R
import com.example.flagquiz.databinding.FragmentAboutScreenBinding


class AboutScreen : Fragment(R.layout.fragment_about_screen) {
    private val binding: FragmentAboutScreenBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}
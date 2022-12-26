package com.example.flagquiz.ui.screens


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.flagquiz.R
import com.example.flagquiz.data.locale_storage.room.enitities.LevelsEntity
import com.example.flagquiz.databinding.FragmentMainScreenBinding
import com.example.flagquiz.ui.adapters.LevelsAdapter
import com.example.flagquiz.utils.alerter
import com.example.flagquiz.viewmodels.MainScreenViewModel
import com.example.flagquiz.viewmodels.impl.MainScreenViewModelImpl
import com.google.android.material.button.MaterialButton


class MainScreen : Fragment(R.layout.fragment_main_screen) {
    private val viewModel: MainScreenViewModel by viewModels<MainScreenViewModelImpl>()
    private val adapter = LevelsAdapter()
    private val binding: FragmentMainScreenBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openQuizScreenLiveData.observe(this) {
            findNavController().navigate(MainScreenDirections.actionMainScreenToQuizScreen(it))
        }
        viewModel.logoutLiveData.observe(this) {
            findNavController().navigate(MainScreenDirections.actionMainScreenToSignInScreen())
        }
        viewModel.openAboutScreenLiveData.observe(this) {
            findNavController().navigate(MainScreenDirections.actionMainScreenToAboutScreen())
        }
        viewModel.openSettingsScreenLiveData.observe(this) {
            findNavController().navigate(MainScreenDirections.actionMainScreenToSettingsScreen())
        }

        adapter.listenerMessage.observe(this){
            alerter(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.contentMain.list.adapter = adapter
        viewModel.levelsLiveData.observe(viewLifecycleOwner) {
            adapter.submitItems(it)
        }
        adapter.onClick {
            viewModel.openQuizScreen(it.id)
        }


        binding.contentMain.topAppBar.setNavigationOnClickListener {
            toggleLeftDrawer()
        }
        binding.leftDrawerMenu.logout.setOnClickListener {
            logoutDialog()
        }
        binding.leftDrawerMenu.home.setOnClickListener {
            toggleLeftDrawer()
        }
        binding.leftDrawerMenu.about.setOnClickListener {
            viewModel.openAboutScreen()
        }
        binding.leftDrawerMenu.settings.setOnClickListener {
            viewModel.openSettingsScreen()
        }

        viewModel.userLiveData.observe(viewLifecycleOwner){
            binding.leftDrawerMenu.userName.text=it.name
            binding.leftDrawerMenu.coinText.text=it.score.toString()
        }
    }

    private fun toggleLeftDrawer() {
        if (binding.drawerLayout.isDrawerOpen(binding.leftDrawerMenu.root)) {
            binding.drawerLayout.closeDrawer(binding.leftDrawerMenu.root)
        } else {
            binding.drawerLayout.openDrawer(binding.leftDrawerMenu.root)
        }
    }

    @SuppressLint("MissingInflatedId")
    private fun logoutDialog() {
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog).create()
        val customLayout: View = layoutInflater.inflate(R.layout.logout_dialog, null)
        val cancel = customLayout.findViewById<MaterialButton>(R.id.cancel_button)
        val yes = customLayout.findViewById<MaterialButton>(R.id.logout)
        builder.setView(customLayout)
        cancel.setOnClickListener {
            builder.cancel()
        }
        yes.setOnClickListener {
            viewModel.logout()
            builder.cancel()
        }
        builder.setCancelable(false)
        builder.show()
    }
}


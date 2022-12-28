package com.example.flagquiz.viewmodels.impl


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flagquiz.data.locale_storage.shared_pref.LocalStorage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel(){
    val openMainScreenLiveData =MutableLiveData<Int>()
    val openSignInLiveData =MutableLiveData<Unit>()
    private val localStorage=LocalStorage.getLocalStorage()
    init {
        viewModelScope.launch {
            delay(1000)
            if (localStorage.userId==0){
                openSignInLiveData.value=Unit
            } else{
                openMainScreenLiveData.value=localStorage.userId
            }
        }
    }
}
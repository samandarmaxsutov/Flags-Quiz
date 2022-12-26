package com.example.flagquiz.viewmodels

import androidx.lifecycle.LiveData

interface SignInViewModel {
    val openMainScreenLiveData: LiveData<Int>
    val messageLiveData: LiveData<String>
    val errorMessageLiveData: LiveData<Pair<Int, String>>
    fun login(email: String, password:String )

}
package com.example.flagquiz.viewmodels

import androidx.lifecycle.LiveData
import com.example.flagquiz.data.locale_storage.room.enitities.UserEntity

interface SettingsScreenViewModel {
    val openMainScreenLiveData: LiveData<Int>
    val openSignInScreenLiveData:LiveData<Unit>
    val userLiveData:LiveData<UserEntity>
    val messageLiveData: LiveData<String>
    val errorMessageLiveData: LiveData<Pair<Int, String>>
    fun login(email: String, password:String )
    fun delete()
}
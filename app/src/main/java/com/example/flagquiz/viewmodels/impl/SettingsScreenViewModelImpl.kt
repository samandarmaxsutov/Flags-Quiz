package com.example.flagquiz.viewmodels.impl

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flagquiz.data.locale_storage.room.AppDatabase
import com.example.flagquiz.data.locale_storage.room.enitities.UserEntity
import com.example.flagquiz.data.locale_storage.shared_pref.LocalStorage
import com.example.flagquiz.viewmodels.SettingsScreenViewModel

class SettingsScreenViewModelImpl : SettingsScreenViewModel, ViewModel(){
    override val openMainScreenLiveData = MutableLiveData<Int>()
    override val openSignInScreenLiveData = MutableLiveData<Unit>()

    override val userLiveData=MediatorLiveData<UserEntity>()
    override val messageLiveData = MutableLiveData<String>()
    override val errorMessageLiveData = MutableLiveData<Pair<Int, String>>()
    private val localeStorage = LocalStorage.getLocalStorage()
    private val userDao = AppDatabase.getAppDatabase().getUserDao()
    private var user=userDao.getUser(localeStorage.userId)
    init {
        userLiveData.addSource(userDao.getUser2(localeStorage.userId)){
            userLiveData.value=it
            user=it
        }
    }
    override fun login(email: String, password: String) {
        if (email.length !in 3..20) {
            errorMessageLiveData.value = Pair(1, "name error (name length between 3 and 20")
            return
        }
        if (password.length !in 5..10) {
            errorMessageLiveData.value = Pair(2, "password error (password length between 5 and 10")
            return
        }
        if (!password.isDigitsOnly()) {
            errorMessageLiveData.value = Pair(2, "password error (password is only digit)")
            return
        }

        userDao.update(UserEntity(localeStorage.userId, name = email, password=password.toInt(), score = user.score))
        messageLiveData.value="Save all changes"
        openMainScreenLiveData.value=localeStorage.userId
    }

    override fun delete() {
        userDao.delete(user)
        localeStorage.userId=0
        messageLiveData.value="Delete user"
        openSignInScreenLiveData.value=Unit
    }
}
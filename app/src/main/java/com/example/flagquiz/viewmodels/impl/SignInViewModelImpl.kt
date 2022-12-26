package com.example.flagquiz.viewmodels.impl

import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flagquiz.data.locale_storage.room.AppDatabase
import com.example.flagquiz.data.locale_storage.room.enitities.LevelsEntity
import com.example.flagquiz.data.locale_storage.room.enitities.UserEntity
import com.example.flagquiz.data.locale_storage.shared_pref.LocalStorage
import com.example.flagquiz.data.repository.LevelsRepository
import com.example.flagquiz.viewmodels.SignInViewModel

class SignInViewModelImpl : SignInViewModel, ViewModel() {
    override val openMainScreenLiveData = MutableLiveData<Int>()
    override val messageLiveData = MutableLiveData<String>()
    override val errorMessageLiveData = MutableLiveData<Pair<Int, String>>()
    private val localeStorage = LocalStorage.getLocalStorage()
    private val levelsRepository = LevelsRepository.getLevelsRepository().getLevelsName()
    private val userDao = AppDatabase.getAppDatabase().getUserDao()
    private val levelsDao = AppDatabase.getAppDatabase().getLevelsDao()

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

        var users = userDao.findUser(password = password.toInt(), name = email)
        if (users.isNotEmpty()) {
            val userId = users[0].id
            localeStorage.userId = userId
            messageLiveData.value = "You already have an account.\n" +
                    "You are logged into your account"
            openMainScreenLiveData.value = userId
            return
        } else {
            userDao.insert(UserEntity(0, email, password.toInt(), 0))
            users = userDao.findUser(password.toInt(), name = email)
            val userId = users[0].id
            localeStorage.userId = userId
            for (i in levelsRepository) {

                Log.d("levels", "getLevelsName: $i")
                levelsDao.insert(LevelsEntity(0, i, 0, 0, userId))
            }
            messageLiveData.value = "Congratulations, you have successfully registered"
            openMainScreenLiveData.value = userId
        }

    }
}
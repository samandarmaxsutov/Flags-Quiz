package com.example.flagquiz.viewmodels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flagquiz.data.locale_storage.room.AppDatabase
import com.example.flagquiz.data.locale_storage.room.enitities.LevelsEntity
import com.example.flagquiz.data.locale_storage.room.enitities.UserEntity
import com.example.flagquiz.data.locale_storage.shared_pref.LocalStorage

import com.example.flagquiz.viewmodels.MainScreenViewModel

class MainScreenViewModelImpl : MainScreenViewModel,ViewModel(){
    private val localStorage=LocalStorage.getLocalStorage()
    override val openQuizScreenLiveData=MutableLiveData<Int>()
    override val openAboutScreenLiveData=MutableLiveData<Unit>()
    override val openSettingsScreenLiveData=MutableLiveData<Unit>()
    override val logoutLiveData=MutableLiveData<Unit>()
    override val userLiveData=MediatorLiveData<UserEntity>()
    override val levelsLiveData=MediatorLiveData<List<LevelsEntity>>()
    private val  levelsDao=AppDatabase.getAppDatabase().getLevelsDao()
    private val userDao=AppDatabase.getAppDatabase().getUserDao()
    init {

        userLiveData.addSource(userDao.getUser2(localStorage.userId)){
            userLiveData.value=it
        }
        levelsLiveData.
        addSource(levelsDao.getAll(LocalStorage.getLocalStorage().userId)){
            levelsLiveData.value=it
        }
    }
    override fun openQuizScreen(level: Int) {
       openQuizScreenLiveData.value=level
    }

    override fun openAboutScreen() {
        openAboutScreenLiveData.value=Unit
    }

    override fun openSettingsScreen() {
        openSettingsScreenLiveData.value=Unit
    }

    override fun logout() {
        localStorage.userId=0
        logoutLiveData.value=Unit
    }

}
package com.example.flagquiz.viewmodels

import androidx.lifecycle.LiveData
import com.example.flagquiz.data.locale_storage.room.enitities.LevelsEntity
import com.example.flagquiz.data.locale_storage.room.enitities.UserEntity

interface MainScreenViewModel {
    val openQuizScreenLiveData:LiveData<Int>
    val openAboutScreenLiveData:LiveData<Unit>
    val openSettingsScreenLiveData:LiveData<Unit>
    val logoutLiveData:LiveData<Unit>
    val userLiveData:LiveData<UserEntity>
    val levelsLiveData:LiveData<List<LevelsEntity>>
    fun openQuizScreen(level:Int)
    fun openAboutScreen()
    fun openSettingsScreen()

    fun logout()
}

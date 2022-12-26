package com.example.flagquiz.app

import android.app.Application
import com.example.flagquiz.data.locale_storage.room.AppDatabase
import com.example.flagquiz.data.locale_storage.shared_pref.LocalStorage
import com.example.flagquiz.data.repository.LevelsRepository

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        LocalStorage.init(this)
        AppDatabase.init(this)
        LevelsRepository.init()
    }
}
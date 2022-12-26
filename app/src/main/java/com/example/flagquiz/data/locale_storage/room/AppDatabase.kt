package com.example.flagquiz.data.locale_storage.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.flagquiz.data.locale_storage.room.daos.LevelsDao
import com.example.flagquiz.data.locale_storage.room.daos.UserDao
import com.example.flagquiz.data.locale_storage.room.enitities.LevelsEntity
import com.example.flagquiz.data.locale_storage.room.enitities.UserEntity

@Database(entities = [UserEntity::class, LevelsEntity::class], version = 1)
abstract class AppDatabase :RoomDatabase(){
    companion object{
        private var appDatabase:AppDatabase?=null
        fun  init(context: Context){
          if(appDatabase==null){
              appDatabase= Room.databaseBuilder(context,AppDatabase::class.java,"appDatabase").
                      allowMainThreadQueries()
                  .build()
          }
        }
        fun getAppDatabase()= appDatabase!!
    }

    abstract fun getUserDao(): UserDao

    abstract fun getLevelsDao():LevelsDao

}
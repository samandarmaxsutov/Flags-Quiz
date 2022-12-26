package com.example.flagquiz.data.locale_storage.shared_pref

import android.content.Context
import com.example.flagquiz.utils.SharedPreferenceHelper

class LocalStorage private constructor(context: Context):SharedPreferenceHelper(context){

    companion object{
        private var storage:LocalStorage?=null
        fun init(context: Context){
            if (storage==null){
                storage= LocalStorage(context)
            }
        }

        fun getLocalStorage()= storage!!
    }

    var userId:Int by ints(0)

}
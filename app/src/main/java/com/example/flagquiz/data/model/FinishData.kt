package com.example.flagquiz.data.model

import com.example.flagquiz.data.locale_storage.room.enitities.LevelsEntity

data class FinishData(
    val level:String,
    val correctAnswers:Int,
    val size:Int,
    val userId:Int,
    val levelsEntity:LevelsEntity
)

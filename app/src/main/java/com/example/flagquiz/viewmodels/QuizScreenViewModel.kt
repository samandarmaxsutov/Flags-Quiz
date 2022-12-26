package com.example.flagquiz.viewmodels

import androidx.lifecycle.LiveData
import com.example.flagquiz.data.locale_storage.room.enitities.LevelsEntity
import com.example.flagquiz.data.model.FinishData
import com.example.flagquiz.data.model.QuestionRandomData

interface QuizScreenViewModel {
    val currentPositionLiveData:LiveData<String>
    val correctAnswersLiveData:LiveData<String>
    val questionLiveData:LiveData<QuestionRandomData>
    val finishLiveData:LiveData<FinishData>

    fun nextQuiz(answer:String)
    fun getQuiz(id:Int)
    fun update(entity: LevelsEntity)

}
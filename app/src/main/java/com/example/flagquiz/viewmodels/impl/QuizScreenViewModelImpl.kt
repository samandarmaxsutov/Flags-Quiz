package com.example.flagquiz.viewmodels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flagquiz.data.locale_storage.room.AppDatabase
import com.example.flagquiz.data.locale_storage.room.enitities.LevelsEntity
import com.example.flagquiz.data.locale_storage.room.enitities.UserEntity
import com.example.flagquiz.data.locale_storage.shared_pref.LocalStorage
import com.example.flagquiz.data.model.FinishData
import com.example.flagquiz.data.model.QuestionRandomData
import com.example.flagquiz.data.repository.LevelsRepository
import com.example.flagquiz.viewmodels.QuizScreenViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuizScreenViewModelImpl : QuizScreenViewModel,ViewModel() {

    private var levelId=0
    private val localStorage=LocalStorage.getLocalStorage()
    private var level="Level"
    private var currentPosition=0
    private val levelsDao=AppDatabase.getAppDatabase().getLevelsDao()
    private val userDao=AppDatabase.getAppDatabase().getUserDao()
    private var correctAnswers=0
    override val currentPositionLiveData=MutableLiveData<String>()
    override val correctAnswersLiveData=MutableLiveData<String>()
    override val questionLiveData=MutableLiveData<QuestionRandomData>()
    override val finishLiveData=MutableLiveData<FinishData>()


    private val questionList=ArrayList<QuestionRandomData>()

    override fun nextQuiz(answer: String) {

        if (currentPosition<questionList.size){
            if (currentPosition==questionList.size-1){
                if (answer==questionList[currentPosition].answer){
                    correctAnswers++
                }
                currentPosition++
                val levelsEntity=levelsDao.getLevel(levelId)
                finishLiveData.value= FinishData(level,correctAnswers,questionList.size,localStorage.userId,levelsEntity)
                return
            }
            else{
                if (answer==questionList[currentPosition].answer){
                    correctAnswers++
                }
                currentPosition++

            }
            questionLiveData.value=questionList[currentPosition]
            correctAnswersLiveData.value="Correct answers: $correctAnswers"
            currentPositionLiveData.value="$level : ${currentPosition+1}/${questionList.size}"
        }


    }

    override fun getQuiz(id: Int) {
        levelId=id
        level=levelsDao.getLevel(id).levels
        questionList.clear()
        currentPosition=0

        correctAnswers=0

        questionList.addAll(LevelsRepository.getLevelsRepository().generate(level))
        questionLiveData.value=questionList[currentPosition]
        correctAnswersLiveData.value="Correct answers: $correctAnswers"
        currentPositionLiveData.value="$level : ${currentPosition+1}/${questionList.size}"
    }


    override fun update(entity: LevelsEntity) {
        levelsDao.update(entity)
        val user=userDao.getUser(localStorage.userId)
        userDao.update(UserEntity(user.id,user.name,user.password,levelsDao.getCount(user.id)))
    }

}
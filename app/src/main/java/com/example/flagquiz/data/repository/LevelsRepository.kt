package com.example.flagquiz.data.repository

import android.util.Log
import com.example.flagquiz.R
import com.example.flagquiz.data.model.QuestionData
import com.example.flagquiz.data.model.QuestionRandomData
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.log

class LevelsRepository {

    private val levels=HashMap<String,List<QuestionRandomData>>()
    private val levelsName= arrayListOf(
        "Level 1",
        "Level 2",
        "Level 3",
        "Level 4",
        "Level 5",
        "Level 6",
        "Level 7",
        "Level 8",
        "Level 9",
        "Level 10",
        "Level 11",
        "Level 12",
        "Level 13",
        "Level 14",
        "Level 15",
        "Level 16",
        "Level 17",
        "Level 18",
        "Level 19",
        "Level 20",
    )

    companion object{
        private var levelsRepository:LevelsRepository?=null
        fun init(){
            if (levelsRepository==null){
                levelsRepository= LevelsRepository()
            }
        }
        fun getLevelsRepository()= levelsRepository!!
    }


    init {

        for (i in levelsName){
            levels[i]= arrayListOf(
                QuestionRandomData(
                    "Argentina",
                    "Afghanistan",
                    "Albania",
                    "Algeria",
                    "Argentina",
                    R.drawable.ar),
                QuestionRandomData(
                    "Canada",
                    "Chad",
                    "Canada",
                    "Cambodia",
                    "Canada",
                    R.drawable.ca
                ),
                QuestionRandomData(
                    "Cuba",
                    "Croatia",
                    "Cyprus",
                    "Czechia",
                    "Cuba",
                    R.drawable.cu
                ),
                QuestionRandomData(
                    "Japan",
                    "Ireland",
                    "Japan",
                    "Italy",
                    "India",
                    R.drawable.jp
                ),
                QuestionRandomData(
                    "Mexico",
                    "Afghanistan",
                    "Albania",
                    "Mexico",
                    "Argentina",
                    R.drawable.mx
                ),
                QuestionRandomData(
                    "Australia",
                    "Chad",
                    "Canada",
                    "Cambodia",
                    "Australia",
                    R.drawable.au
                ),
                QuestionRandomData(
                    "Malaysia",
                    "Croatia",
                    "Malaysia",
                    "Czechia",
                    "Cuba",
                    R.drawable.my
                ),
                QuestionRandomData(
                    "Mongolia",
                    "Ireland",
                    "Mongolia",
                    "Italy",
                    "India",
                    R.drawable.mn
                ),
                QuestionRandomData(
                    "Kazakhstan",
                    "Japan",
                    "Kazakhstan",
                    "Jordan",
                    "Kuwait",
                    R.drawable.kz
                ),
                QuestionRandomData(
                    "Italy",
                    "Japan",
                    "Kazakhstan",
                    "Italy",
                    "Kuwait",
                    R.drawable.it
                ),

                )
        }
    }

    fun generate(levels_name: String):List<QuestionRandomData>{
//
//        val level=ArrayList<QuestionRandomData>()
//        val l= levels[levels_name]
//
//        l?.shuffled()
//
//        Log.d("oo1", "generate:${l?.size} $levels_name ")
//        val questionIndexList=ArrayList<Int>()
//        while (l?.size!! >questionIndexList.size){
//
//            Log.d("oo2", "generate:${questionIndexList.toString()} $levels_name ")
//            val index =Random().nextInt((l.size))
//
//            Log.d("oo3", "generate:${index} ")
//            if (!questionIndexList.contains(index)){
//                questionIndexList.add(index)
//                val list2=options(index,l.size-1)
//
//                Log.d("oo4", "generate:${list2.toString()} ")
//              val questionRandomData=when(Random().nextInt((3))){
//                    0->{
//                        QuestionRandomData(
//                            l[index].answer,
//                            l[index].answer,
//                            l[list2[0]].answer,
//                            l[list2[1]].answer,
//                            l[list2[2]].answer,
//                            l[index].image,
//                        )
//                    }
//                    1->{
//                        QuestionRandomData(
//                            l[index].answer,
//                            l[list2[0]].answer,
//                            l[index].answer,
//                            l[list2[1]].answer,
//                            l[list2[2]].answer,
//                            l[index].image,
//                        )
//                    }
//                    2->{
//                        QuestionRandomData(
//                            l[index].answer,
//                            l[list2[0]].answer,
//                            l[list2[1]].answer,
//                            l[index].answer,
//                            l[list2[2]].answer,
//                            l[index].image,
//                        )
//                    }
//                    else -> {
//                      QuestionRandomData(
//                          l[index].answer,
//                          l[list2[0]].answer,
//                          l[list2[1]].answer,
//                          l[list2[2]].answer,
//                          l[index].answer,
//                          l[index].image,
//                      )
//                  }
//              }
//              level.add(questionRandomData)
//            }
//        }
//        return level

        return levels[levels_name]!!.shuffled()
    }
    private fun options(index:Int, size:Int):ArrayList<Int>{
        val list=ArrayList<Int>()
        for (i in 0 until size){
            list.add(i)
        }
        list.removeAt(index)
        list.shuffled()
        for (i in 0 until list.size){
            if (i>2){
             list.removeAt(i)
         }
        }
        return list
    }

    fun getLevelsName():ArrayList<String>{
        return levelsName
    }
}
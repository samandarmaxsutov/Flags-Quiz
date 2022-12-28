package com.example.flagquiz.test

import kotlin.random.Random

fun main() {
    val questionRepository = QuestionRepository()
    questionRepository.generate()
    questionRepository.show()
}

//test generator
class QuestionRepository() {
    private val flagsData = ArrayList<FlagsData>()
    private val questionData = ArrayList<QuestionData>()

    init {
        flagsData.add(FlagsData("1", 1))
        flagsData.add(FlagsData("2", 2))
        flagsData.add(FlagsData("3", 3))
        flagsData.add(FlagsData("4", 4))
        flagsData.add(FlagsData("5", 5))
        flagsData.add(FlagsData("6", 6))
        flagsData.add(FlagsData("7", 7))

    }

    fun generate() {
        val list = ArrayList<Int>()

        while (flagsData.size > questionData.size) {
            val randomIndex = Random.nextInt(flagsData.size)
            val options = options(randomIndex, flagsData.size)
            val randomOptionsState = Random.nextInt(4)
            if (!list.contains(randomIndex)) {
                println(randomIndex)
                list.add(randomIndex)
                when (randomOptionsState) {
                    0 -> {
                        questionData.add(
                            QuestionData(
                                flagsData[randomIndex].answer,
                                flagsData[options[0]].answer,
                                flagsData[options[1]].answer,
                                flagsData[options[2]].answer,
                                flagsData[randomIndex].answer,
                                flagsData[randomIndex].image
                            )
                        )
                    }
                    1 -> {
                        questionData.add(
                            QuestionData(
                                flagsData[randomIndex].answer,
                                flagsData[options[0]].answer,
                                flagsData[options[1]].answer,
                                flagsData[randomIndex].answer,
                                flagsData[2].answer,
                                flagsData[randomIndex].image
                            )
                        )
                    }
                    2 -> {
                        questionData.add(
                            QuestionData(
                                flagsData[randomIndex].answer,
                                flagsData[options[0]].answer,
                                flagsData[randomIndex].answer,
                                flagsData[options[2]].answer,
                                flagsData[1].answer,
                                flagsData[randomIndex].image
                            )
                        )
                    }
                    else-> {
                        questionData.add(
                            QuestionData(
                                flagsData[randomIndex].answer,
                                flagsData[randomIndex].answer,
                                flagsData[options[1]].answer,
                                flagsData[options[2]].answer,
                                flagsData[randomIndex].answer,
                                flagsData[randomIndex].image
                            )
                        )
                    }
                }
            }
        }
    }

    private fun options(index: Int, size: Int): ArrayList<Int> {
        val list = ArrayList<Int>()
        for (i in 0 until size) {
            if (i != index)
                list.add(i)
        }
        list.shuffle()
        for (i in 0 until list.size) {
            if (i > 2) {
                list.remove(element = i)
            }
        }
        return list
    }

    fun show() {
        questionData.forEach {
            println(it.toString())
        }
    }
}

data class FlagsData(
    val answer: String,
    val image: Int
)

data class QuestionData(
    val answer: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val image: Int
)
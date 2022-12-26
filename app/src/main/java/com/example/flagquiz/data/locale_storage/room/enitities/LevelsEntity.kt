package com.example.flagquiz.data.locale_storage.room.enitities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "levels",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        childColumns = ["userId"],
        parentColumns = ["id"],
        onDelete = ForeignKey.CASCADE
    )])
data class LevelsEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val levels:String,
    val correct:Int,
    val allQuestions:Int,
    val userId:Int,
)

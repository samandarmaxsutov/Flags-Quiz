package com.example.flagquiz.data.locale_storage.room.enitities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val password: Int,
    val score:Int
)
package com.example.flagquiz.data.locale_storage.room.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flagquiz.data.locale_storage.room.enitities.LevelsEntity


@Dao
interface LevelsDao {
    @Insert
    fun insert(levelsEntity: LevelsEntity)

    @Update
    fun update(levelsEntity: LevelsEntity)

    @Query("select * from levels where userId=:user_id  ")
    fun getAll(user_id:Int):LiveData<List<LevelsEntity>>

    @Delete
    fun delete(levelsEntity: LevelsEntity)

    @Query("select * from levels where id=:id ")
    fun getLevel(id:Int):LevelsEntity

    @Query("select sum(correct) from levels where userId=:user_id")
    fun getCount(user_id: Int):Int
}
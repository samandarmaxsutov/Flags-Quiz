package com.example.flagquiz.data.locale_storage.room.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flagquiz.data.locale_storage.room.enitities.UserEntity

@Dao
interface UserDao {

    @Query("select * from users where password==:password and name= :name ")
    fun findUser(password: Int, name:String):List<UserEntity>

    @Query ("select * from users where id=:id")
    fun getUser(id:Int):UserEntity
    @Query ("select * from users where id=:id")
    fun getUser2(id:Int):LiveData<UserEntity>

    @Insert
    fun insert(userEntity: UserEntity)

    @Update
    fun update(userEntity: UserEntity)

    @Delete
    fun delete(userEntity: UserEntity)


}
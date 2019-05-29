package com.example.darkfox.trainingnotes.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface BaseDao<T> {


    @Insert(onConflict = REPLACE)
    fun insert(item: T)

    @Insert(onConflict = REPLACE)
    fun insertList(items: List<T>)

    @Delete
    fun delete(item: T)

    @Delete
    fun deleteList(items: List<T>)
}
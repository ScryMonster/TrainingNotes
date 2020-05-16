package com.example.darkfox.trainingnotes.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.darkfox.trainingnotes.models.dto.Account


@Dao
interface AccountDao : BaseDao<Account>{

    @Query("SELECT * from accounts WHERE fireBaseId =:id")
    fun getAccount(id:String) : Account?
}
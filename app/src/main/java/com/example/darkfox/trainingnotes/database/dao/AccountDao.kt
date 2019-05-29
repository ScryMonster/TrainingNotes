package com.example.darkfox.trainingnotes.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.darkfox.trainingnotes.dto.Account
import kotlinx.coroutines.Deferred


@Dao
interface AccountDao : BaseDao<Account>{

    @Query("SELECT * from accounts WHERE fireBaseId =:id")
    fun getAccount(id:String) : List<Account>
}
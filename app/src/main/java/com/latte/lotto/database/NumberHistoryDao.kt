package com.latte.lotto.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NumberHistoryDao {

    @Query("SELECT * FROM number_history")
    fun getAll(): List<NumberHistory>

    @Insert
    fun insert(numberHistory: NumberHistory)
}
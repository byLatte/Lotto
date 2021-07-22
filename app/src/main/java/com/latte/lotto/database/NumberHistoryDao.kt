package com.latte.lotto.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NumberHistoryDao {

    @Query("SELECT * FROM number_history ORDER BY reg_dt DESC")
    fun getAll(): LiveData<List<NumberHistory>>

    @Insert
    fun insert(numberHistory: NumberHistory)

    @Delete
    fun delete(numberHistory: NumberHistory)

    @Query("DELETE FROM number_history")
    fun deleteAll()
}
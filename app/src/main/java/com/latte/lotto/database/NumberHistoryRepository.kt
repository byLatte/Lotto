package com.latte.lotto.database

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.util.concurrent.Executors


private const val TAG = "NumberHistoryRepository"
private const val DATABASE_NAME = "lotto-database"

class NumberHistoryRepository private constructor(context: Context){

    private val database: AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val numberHistoryDao = database.numberHistoryDao()
    private val executor = Executors.newSingleThreadExecutor()

    // 내부 DAO에서 실행 하기 위함 (백그라운드 스레드 사용)
    fun addNumberHistory(numberHistory: NumberHistory){
        executor.execute {
            Log.d(TAG,"HISTORY INSERT")
            numberHistoryDao.insert(numberHistory)
        }
    }

    fun getNumberHistory() : LiveData<List<NumberHistory>> = numberHistoryDao.getAll()

    companion object{
        private var INSTANCE: NumberHistoryRepository? = null

        fun init(context: Context){
            if(INSTANCE == null){
                INSTANCE = NumberHistoryRepository(context)
            }
        }

        fun get():NumberHistoryRepository{
            return INSTANCE ?:
            throw IllegalStateException("numberHistory Exception!! ")
        }

    }

}

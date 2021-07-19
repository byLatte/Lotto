package com.latte.lotto.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * https://developer.android.com/training/data-storage/room
 */
@Database(entities = [NumberHistory::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun numberHistoryDao() : NumberHistoryDao
}
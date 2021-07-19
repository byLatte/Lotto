package com.latte.lotto.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*

@Entity(tableName = "number_history")
data class NumberHistory (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val regDt: Date = Date(),
    var number1: Int,
    var number2: Int,
    var number3: Int,
    var number4: Int,
    var number5: Int,
    var number6: Int,
)

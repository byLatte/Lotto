package com.latte.lotto.win


import androidx.lifecycle.ViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class WinViewModel : ViewModel() {

    // 기준데이터 21시 이후 1회차 적용
    private val stanDrwNo: Int = 973
    private val stanDate = "2021-07-24 21:00:00"

    // 최근 회차 구하기
    fun curDrwNo(): Int{
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val cDate: Date = Date()
        val sDate: Date = dateFormat.parse(stanDate)
        val diff: Long = cDate.time - sDate.time
        val nextNo: Long = (diff / (86400*1000*7))

        return stanDrwNo+nextNo.toInt()
    }
}
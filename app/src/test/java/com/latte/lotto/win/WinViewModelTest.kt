package com.latte.lotto.win

import org.junit.Test
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "WinViewModelTest"

class WinViewModelTest{

    private val stanDrwNo: Int = 973
    private val stanDate = "2021-07-10 21:00:00"
//    private val endDate = "2021-07-24 20:23:45"

    @Test
    fun curDrwNo(){
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val cDate: Date = Date()
//        val cDate: Date = dateFormat.parse(endDate)
        val sDate: Date = dateFormat.parse(stanDate)
        val diff: Long = cDate.time - sDate.time
        val nextNo: Long = (diff / (86400*1000*7))



        System.out.println(" >>  ${stanDrwNo+nextNo.toInt()}")
    }
}
package com.latte.lotto.win

import org.junit.Test
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "WinViewModelTest"

class WinViewModelTest{

    private val stanDrwNo: Int = 971
    private val stanDate: String = "2021-07-10 21:00:00"
    private var curDrwNo: Int = 0
    private lateinit var curDate: Date
    private lateinit var drwData: MutableMap<Int,String>
    private val cal: Calendar = Calendar.getInstance()

    @Test
    fun getCurDrwNo(){
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val cDate: Date = Date()
        val sDate: Date = dateFormat.parse(stanDate)
        val diff: Long = cDate.time - sDate.time
        val nextNo: Long = (diff / (86400*1000*7))

        System.out.println(" >>  ${stanDrwNo+nextNo.toInt()}")
        curDrwNo = stanDrwNo+nextNo.toInt()
        cal.time = sDate
        cal.add(Calendar.DATE,7*nextNo.toInt())
        curDate = cal.time

        drwData = mutableMapOf()
        for(i in 0 .. 20){
            drwData[curDrwNo-i] = dateCal(curDate,i)
        }
        System.out.println(" DATE >> ${drwData.toString()} ")
    }

    fun dateCal(dt: Date, d: Int): String{
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        cal.time = dt
        cal.add(Calendar.DATE, d*(-7))
        return dateFormat.format(cal.time)
    }
}
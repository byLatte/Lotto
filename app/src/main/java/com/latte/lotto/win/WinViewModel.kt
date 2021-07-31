package com.latte.lotto.win


import android.util.Log
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "WinViewModel"

class WinViewModel : ViewModel() {

    // 기준데이터 21시 이후 1회차 적용
    private val stanDrwNo: Int = 973
    private val stanDate = "2021-07-24 21:00:00"
    private var curDrwNo: Int = 0
    private val winApi = WinApi.create()

    fun getWinInfo(){
        winApi.fetchData().enqueue(object : Callback<WinItem>{
            override fun onResponse(call: Call<WinItem>, response: Response<WinItem>) {
                Log.d(TAG,"SUCCESS : ${response.body()}")
            }

            override fun onFailure(call: Call<WinItem>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

//    // 최근 회차 구하기
    fun getCurDrwNo(){
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val cDate: Date = Date()
        val sDate: Date = dateFormat.parse(stanDate)
        val diff: Long = cDate.time - sDate.time
        val nextNo: Long = (diff / (86400*1000*7))

        curDrwNo = stanDrwNo+nextNo.toInt()
    }

}
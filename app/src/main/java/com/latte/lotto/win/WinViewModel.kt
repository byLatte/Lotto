package com.latte.lotto.win


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    private val winApi = WinApi.create()

    fun getWinInfo(drwNo: Int): LiveData<WinItem>{
        val respLiveData: MutableLiveData<WinItem> = MutableLiveData()
        winApi.getWinInfo(drwNo).enqueue(object: Callback<WinItem>{
            override fun onResponse(call: Call<WinItem>, response: Response<WinItem>) {
                respLiveData.value = response.body()
            }

            override fun onFailure(call: Call<WinItem>, t: Throwable) {
                Log.d(TAG,"API Failure : $t")
            }
        })
        return respLiveData
    }

//    // 최근 회차 구하기
    fun getCurDrwNo(): Int{
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val cDate: Date = Date()
        val sDate: Date = dateFormat.parse(stanDate)
        val diff: Long = cDate.time - sDate.time
        val nextNo: Long = (diff / (86400*1000*7))

        return stanDrwNo+nextNo.toInt()
    }

}
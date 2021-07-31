package com.latte.lotto.win

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WinApi {

    @GET("/common.do?method=getLottoNumber&drwNo=861")
    fun fetchData(): Call<WinItem>

    companion object{
        private val baseUrl = "https://www.dhlottery.co.kr"

        fun create(): WinApi{
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(WinApi::class.java)
        }
    }
}
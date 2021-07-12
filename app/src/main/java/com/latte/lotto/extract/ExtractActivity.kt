package com.latte.lotto.extract

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.latte.lotto.R
import com.latte.lotto.databinding.ActivityExtractBinding

private const val TAG = "ExtractActivity"

class ExtractActivity : AppCompatActivity() {

    private lateinit var extractBinding: ActivityExtractBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val numberList = mutableListOf<Int>()
        for(i in 1..45){
            numberList.add(i)
        }

        extractBinding = DataBindingUtil.setContentView(this,R.layout.activity_extract)

        extractBinding.extractButton.setOnClickListener {
            val resultNumber = mutableListOf<Int>()
            resultNumber.clear()
            numberList.shuffle()

            for(i in 0..5){
                resultNumber.add(numberList[i])
            }

            resultNumber.sort()
            extractBinding.resultNumberTextView.text = resultNumber.toString()
        }
    }

}
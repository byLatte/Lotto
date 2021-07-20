package com.latte.lotto

import android.content.Context
import java.lang.Exception

private const val TAG = "NumberModel"

class NumberModel {

    private val numberDataModel: MutableList<Int> = mutableListOf()

    fun getNumberDataModel() = numberDataModel

    init {

        numberDataModel.add(R.drawable.number_1)
        numberDataModel.add(R.drawable.number_2)
        numberDataModel.add(R.drawable.number_3)
        numberDataModel.add(R.drawable.number_4)
        numberDataModel.add(R.drawable.number_5)
        numberDataModel.add(R.drawable.number_6)
        numberDataModel.add(R.drawable.number_7)
        numberDataModel.add(R.drawable.number_8)
        numberDataModel.add(R.drawable.number_9)
        numberDataModel.add(R.drawable.number_10)
        numberDataModel.add(R.drawable.number_11)
        numberDataModel.add(R.drawable.number_12)
        numberDataModel.add(R.drawable.number_13)
        numberDataModel.add(R.drawable.number_14)
        numberDataModel.add(R.drawable.number_15)
        numberDataModel.add(R.drawable.number_16)
        numberDataModel.add(R.drawable.number_17)
        numberDataModel.add(R.drawable.number_18)
        numberDataModel.add(R.drawable.number_19)
        numberDataModel.add(R.drawable.number_20)
        numberDataModel.add(R.drawable.number_21)
        numberDataModel.add(R.drawable.number_22)
        numberDataModel.add(R.drawable.number_23)
        numberDataModel.add(R.drawable.number_24)
        numberDataModel.add(R.drawable.number_25)
        numberDataModel.add(R.drawable.number_26)
        numberDataModel.add(R.drawable.number_27)
        numberDataModel.add(R.drawable.number_28)
        numberDataModel.add(R.drawable.number_29)
        numberDataModel.add(R.drawable.number_30)
        numberDataModel.add(R.drawable.number_31)
        numberDataModel.add(R.drawable.number_32)
        numberDataModel.add(R.drawable.number_33)
        numberDataModel.add(R.drawable.number_34)
        numberDataModel.add(R.drawable.number_35)
        numberDataModel.add(R.drawable.number_36)
        numberDataModel.add(R.drawable.number_37)
        numberDataModel.add(R.drawable.number_38)
        numberDataModel.add(R.drawable.number_39)
        numberDataModel.add(R.drawable.number_40)
        numberDataModel.add(R.drawable.number_41)
        numberDataModel.add(R.drawable.number_42)
        numberDataModel.add(R.drawable.number_43)
        numberDataModel.add(R.drawable.number_44)
        numberDataModel.add(R.drawable.number_45)
    }

    companion object{
        private var INSTANCE: NumberModel? = null

        private lateinit var context: Context

        fun init(): NumberModel{
            if(INSTANCE == null){
                INSTANCE = NumberModel()
            }
            return INSTANCE as NumberModel
        }
        fun get() : NumberModel {
            return INSTANCE ?:
            throw Exception("Number MODEL INSTANCE EXCEPTION")
        }
    }
}
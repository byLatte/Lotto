package com.latte.lotto.extract

import androidx.lifecycle.ViewModel
import com.latte.lotto.NumberModel
import com.latte.lotto.database.NumberHistory
import com.latte.lotto.database.NumberHistoryRepository
import java.util.*
import kotlin.random.Random

class ExtractViewModel : ViewModel() {

    private val numberHistoryRepository = NumberHistoryRepository.get()
    private val numberModel: NumberModel = NumberModel.get()
    private val numberData: MutableList<Int> = numberModel.getNumberDataModel()

    fun extractNumber() : MutableList<Int>{
        val resultNumber = mutableListOf<Int>()

        // 번호추출
        while(resultNumber.size < 6){
            val extractNumber = Random.nextInt(0,45)
            if(!resultNumber.contains(extractNumber)){
                resultNumber.add(extractNumber)
            }
            if(resultNumber.size > 6) break
        }
        resultNumber.sort()

        // 이력등록
        var numberHistory = NumberHistory(
            0,Date(),resultNumber[0],resultNumber[1],resultNumber[2],resultNumber[3],resultNumber[4],resultNumber[5],
        )
        addNumberHistory(numberHistory)

        val resIdList = mutableListOf<Int>()
        for(i in resultNumber){
            resIdList.add(numberData[i])
        }
        return resIdList
    }

    private fun addNumberHistory(numberHistory: NumberHistory){
        numberHistoryRepository.addNumberHistory(numberHistory)
    }

}
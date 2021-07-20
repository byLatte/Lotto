package com.latte.lotto.history

import androidx.lifecycle.ViewModel
import com.latte.lotto.database.NumberHistory
import com.latte.lotto.database.NumberHistoryRepository

class NumberHistoryViewModel : ViewModel() {

    private val numberHistoryRepository = NumberHistoryRepository.get()
    val numberHistory = numberHistoryRepository.getNumberHistory()

}
package com.latte.lotto.history

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.latte.lotto.NumberModel
import com.latte.lotto.R
import com.latte.lotto.database.NumberHistory
import com.latte.lotto.database.NumberHistoryRepository
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.reflect.typeOf

private const val TAG = "RecyclerAdapter"

class NumberHistoryRecyclerAdapter(val numbers: List<NumberHistory>) : RecyclerView.Adapter<NumberHistoryRecyclerAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberHistoryRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.number_history_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(numbers[position])
    }

    override fun getItemCount(): Int = numbers.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){

        //번호 Res 정보
        private val numberModel: NumberModel = NumberModel.get()
        private val numberData: MutableList<Int> = numberModel.getNumberDataModel()

        private val numberHistoryRepository = NumberHistoryRepository.get()

        fun bindItem(number: NumberHistory){
            val itemView = view.findViewById<TextView>(R.id.date_text_view)
            val historyNumber1 = view.findViewById<ImageView>(R.id.history_image_view_1)
            val historyNumber2 = view.findViewById<ImageView>(R.id.history_image_view_2)
            val historyNumber3 = view.findViewById<ImageView>(R.id.history_image_view_3)
            val historyNumber4 = view.findViewById<ImageView>(R.id.history_image_view_4)
            val historyNumber5 = view.findViewById<ImageView>(R.id.history_image_view_5)
            val historyNumber6 = view.findViewById<ImageView>(R.id.history_image_view_6)
            historyNumber1.setImageResource(numberData[number.number1])
            historyNumber2.setImageResource(numberData[number.number2])
            historyNumber3.setImageResource(numberData[number.number3])
            historyNumber4.setImageResource(numberData[number.number4])
            historyNumber5.setImageResource(numberData[number.number5])
            historyNumber6.setImageResource(numberData[number.number6])

            val date = number.regDt
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            itemView.text = dateFormat.format(date).toString()

            val removeImageView = view.findViewById<ImageView>(R.id.history_remove_image_view)
            removeImageView.setOnClickListener{
                numberHistoryRepository.deleteNumberHistory(number)
            }
        }
    }
}
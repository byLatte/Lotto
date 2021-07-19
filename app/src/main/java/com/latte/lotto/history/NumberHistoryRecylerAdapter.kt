package com.latte.lotto.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.latte.lotto.R
import com.latte.lotto.database.NumberHistory

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

        fun bindItem(number: NumberHistory){
            val itemView = view.findViewById<TextView>(R.id.date_text_view)
            itemView.text = number.regDt.toString()
        }
    }


}
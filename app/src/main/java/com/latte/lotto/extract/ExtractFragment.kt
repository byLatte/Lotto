package com.latte.lotto.extract

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.latte.lotto.R
import com.latte.lotto.database.NumberHistory
import com.latte.lotto.databinding.FragmentExtractBinding
import com.latte.lotto.history.NumberHistoryViewModel
import java.util.*
import kotlin.random.Random

private const val TAG = "ExtractFragment"

class ExtractFragment : Fragment() {

    private lateinit var extractBinding: FragmentExtractBinding
    private lateinit var numberDataModel: MutableList<ExtractNumberModel>
    private var callback: ExtractCallbacks? = null

    private val numberHistoryViewModel: NumberHistoryViewModel by lazy{
        ViewModelProvider(this).get(NumberHistoryViewModel::class.java)
    }

    interface ExtractCallbacks{
        fun extractToMain()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as ExtractCallbacks
    }

    override fun onDetach() {
        super.onDetach()
        callback = null

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNumberData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        extractBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_extract,container,false)

        extractBinding.extractButton.setOnClickListener {
            val resultNumber = mutableListOf<Int>()

            while(resultNumber.size < 6){
                val extractNumber = Random.nextInt(0,44)
                if(!resultNumber.contains(extractNumber)){
                    resultNumber.add(extractNumber)
                }
                if(resultNumber.size > 6) break
            }
            resultNumber.sort()

            extractBinding.resultImageView1.setImageResource(numberDataModel[resultNumber[0]].numberRes)
            extractBinding.resultImageView2.setImageResource(numberDataModel[resultNumber[1]].numberRes)
            extractBinding.resultImageView3.setImageResource(numberDataModel[resultNumber[2]].numberRes)
            extractBinding.resultImageView4.setImageResource(numberDataModel[resultNumber[3]].numberRes)
            extractBinding.resultImageView5.setImageResource(numberDataModel[resultNumber[4]].numberRes)
            extractBinding.resultImageView6.setImageResource(numberDataModel[resultNumber[5]].numberRes)

            var numberHistory = NumberHistory(
                0,Date(),3,4,5,6,7,8
            )

            numberHistoryViewModel.addHistory(numberHistory)
        }

        extractBinding.mainActivityButton.setOnClickListener {
            callback?.extractToMain()
        }
        return extractBinding.root
    }

    private fun initNumberData(){
        numberDataModel = mutableListOf()

        numberDataModel.add(ExtractNumberModel(1,R.drawable.number_1))
        numberDataModel.add(ExtractNumberModel(2,R.drawable.number_2))
        numberDataModel.add(ExtractNumberModel(3,R.drawable.number_3))
        numberDataModel.add(ExtractNumberModel(4,R.drawable.number_4))
        numberDataModel.add(ExtractNumberModel(5,R.drawable.number_5))
        numberDataModel.add(ExtractNumberModel(6,R.drawable.number_6))
        numberDataModel.add(ExtractNumberModel(7,R.drawable.number_7))
        numberDataModel.add(ExtractNumberModel(8,R.drawable.number_8))
        numberDataModel.add(ExtractNumberModel(9,R.drawable.number_9))
        numberDataModel.add(ExtractNumberModel(10,R.drawable.number_10))
        numberDataModel.add(ExtractNumberModel(11,R.drawable.number_11))
        numberDataModel.add(ExtractNumberModel(12,R.drawable.number_12))
        numberDataModel.add(ExtractNumberModel(13,R.drawable.number_13))
        numberDataModel.add(ExtractNumberModel(14,R.drawable.number_14))
        numberDataModel.add(ExtractNumberModel(15,R.drawable.number_15))
        numberDataModel.add(ExtractNumberModel(16,R.drawable.number_16))
        numberDataModel.add(ExtractNumberModel(17,R.drawable.number_17))
        numberDataModel.add(ExtractNumberModel(18,R.drawable.number_18))
        numberDataModel.add(ExtractNumberModel(19,R.drawable.number_19))
        numberDataModel.add(ExtractNumberModel(20,R.drawable.number_20))
        numberDataModel.add(ExtractNumberModel(21,R.drawable.number_21))
        numberDataModel.add(ExtractNumberModel(22,R.drawable.number_22))
        numberDataModel.add(ExtractNumberModel(23,R.drawable.number_23))
        numberDataModel.add(ExtractNumberModel(24,R.drawable.number_24))
        numberDataModel.add(ExtractNumberModel(25,R.drawable.number_25))
        numberDataModel.add(ExtractNumberModel(26,R.drawable.number_26))
        numberDataModel.add(ExtractNumberModel(27,R.drawable.number_27))
        numberDataModel.add(ExtractNumberModel(28,R.drawable.number_28))
        numberDataModel.add(ExtractNumberModel(29,R.drawable.number_29))
        numberDataModel.add(ExtractNumberModel(30,R.drawable.number_30))
        numberDataModel.add(ExtractNumberModel(31,R.drawable.number_31))
        numberDataModel.add(ExtractNumberModel(32,R.drawable.number_32))
        numberDataModel.add(ExtractNumberModel(33,R.drawable.number_33))
        numberDataModel.add(ExtractNumberModel(34,R.drawable.number_34))
        numberDataModel.add(ExtractNumberModel(35,R.drawable.number_35))
        numberDataModel.add(ExtractNumberModel(36,R.drawable.number_36))
        numberDataModel.add(ExtractNumberModel(37,R.drawable.number_37))
        numberDataModel.add(ExtractNumberModel(38,R.drawable.number_38))
        numberDataModel.add(ExtractNumberModel(39,R.drawable.number_39))
        numberDataModel.add(ExtractNumberModel(40,R.drawable.number_40))
        numberDataModel.add(ExtractNumberModel(41,R.drawable.number_41))
        numberDataModel.add(ExtractNumberModel(42,R.drawable.number_42))
        numberDataModel.add(ExtractNumberModel(43,R.drawable.number_43))
        numberDataModel.add(ExtractNumberModel(44,R.drawable.number_44))
        numberDataModel.add(ExtractNumberModel(45,R.drawable.number_45))
    }

    companion object{
        fun newInstance():ExtractFragment = ExtractFragment()
    }
}
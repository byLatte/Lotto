package com.latte.lotto.extract

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.latte.lotto.R
import com.latte.lotto.databinding.FragmentExtractBinding
import kotlin.random.Random

private const val TAG = "ExtractFragment"

class ExtractFragment : Fragment() {

    private lateinit var extractBinding: FragmentExtractBinding
    private lateinit var numberDataModel: MutableList<ExtractNumberModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        numberDataModel = mutableListOf()

        numberDataModel.add(ExtractNumberModel(1,R.drawable.number_1))
        numberDataModel.add(ExtractNumberModel(2,R.drawable.number_2))
        numberDataModel.add(ExtractNumberModel(3,R.drawable.number_3))
        numberDataModel.add(ExtractNumberModel(4,R.drawable.number_4))
        numberDataModel.add(ExtractNumberModel(5,R.drawable.number_5))
        numberDataModel.add(ExtractNumberModel(6,R.drawable.number_6))

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        extractBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_extract,container,false)

        extractBinding.extractButton.setOnClickListener {
            extractBinding.resultImageView1.setImageResource(numberDataModel[Random.nextInt(1,6)].numberRes)
            extractBinding.resultImageView2.setImageResource(numberDataModel[Random.nextInt(1,6)].numberRes)
            extractBinding.resultImageView3.setImageResource(numberDataModel[Random.nextInt(1,6)].numberRes)
            extractBinding.resultImageView4.setImageResource(numberDataModel[Random.nextInt(1,6)].numberRes)
            extractBinding.resultImageView5.setImageResource(numberDataModel[Random.nextInt(1,6)].numberRes)
            extractBinding.resultImageView6.setImageResource(numberDataModel[Random.nextInt(1,6)].numberRes)
        }

        return extractBinding.root
    }
}
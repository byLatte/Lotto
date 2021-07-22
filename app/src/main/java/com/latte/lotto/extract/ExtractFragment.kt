package com.latte.lotto.extract

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.latte.lotto.R
import com.latte.lotto.databinding.FragmentExtractBinding
import com.latte.lotto.history.NumberHistoryViewModel

private const val TAG = "ExtractFragment"

class ExtractFragment : Fragment() {

    private lateinit var extractBinding: FragmentExtractBinding

    private var callback: Callbacks? = null

    private val extractViewModel: ExtractViewModel by lazy{
        ViewModelProvider(this).get(ExtractViewModel::class.java)
    }
    private val numberHistoryViewModel: NumberHistoryViewModel by lazy{
        ViewModelProvider(this).get(NumberHistoryViewModel::class.java)
    }

    interface Callbacks{
        fun extractToMain()
        fun extractDialogShow()
        fun extractDialogDestroy()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as Callbacks
    }

    override fun onDetach() {
        super.onDetach()
        callback = null

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        extractBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_extract,container,false)

        extractBinding.extractButton.setOnClickListener {
            callback?.extractDialogShow()
            Handler(Looper.getMainLooper()).postDelayed({
                callback?.extractDialogDestroy()
            },3000)
            val resIdList =extractViewModel.extractNumber()

            extractBinding.resultImageView1.setImageResource(resIdList[0])
            extractBinding.resultImageView2.setImageResource(resIdList[1])
            extractBinding.resultImageView3.setImageResource(resIdList[2])
            extractBinding.resultImageView4.setImageResource(resIdList[3])
            extractBinding.resultImageView5.setImageResource(resIdList[4])
            extractBinding.resultImageView6.setImageResource(resIdList[5])

        }

        extractBinding.mainActivityButton.setOnClickListener {
            callback?.extractToMain()
        }
        return extractBinding.root
    }

    companion object{
        fun newInstance():ExtractFragment = ExtractFragment()
    }
}
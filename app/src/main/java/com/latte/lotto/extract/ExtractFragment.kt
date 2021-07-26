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
import com.latte.lotto.MainActivity
import com.latte.lotto.R
import com.latte.lotto.databinding.FragmentExtractBinding
import com.latte.lotto.history.NumberHistoryFragment

private const val TAG = "ExtractFragment"

class ExtractFragment : Fragment(){

    private lateinit var extractBinding: FragmentExtractBinding

    private var callback: Callbacks? = null

    private val extractViewModel: ExtractViewModel by lazy{
        ViewModelProvider(this).get(ExtractViewModel::class.java)
    }
//    private val numberHistoryViewModel: NumberHistoryViewModel by lazy{
//        ViewModelProvider(this).get(NumberHistoryViewModel::class.java)
//    }

    interface Callbacks{
        fun extractDialogShow()
        fun extractDialogDismiss()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as Callbacks
        Log.d(TAG, "extrc Fragment Attach")
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
            resultImageViewVisible(false)
            val resIdList =extractViewModel.extractNumber()

            extractBinding.resultImageView1.setImageResource(resIdList[0])
            extractBinding.resultImageView2.setImageResource(resIdList[1])
            extractBinding.resultImageView3.setImageResource(resIdList[2])
            extractBinding.resultImageView4.setImageResource(resIdList[3])
            extractBinding.resultImageView5.setImageResource(resIdList[4])
            extractBinding.resultImageView6.setImageResource(resIdList[5])

            callback?.extractDialogShow()
            Handler(Looper.getMainLooper()).postDelayed({
                callback?.extractDialogDismiss()
                resultImageViewVisible(true)
            },3000)
        }
        val main = activity as MainActivity
        extractBinding.mainActivityButton.setOnClickListener {
            main.toMainFragment(NumberHistoryFragment.toString())
        }
        return extractBinding.root
    }

    // 번호 결과 보이기/숨기기
    private fun resultImageViewVisible(visible: Boolean){
        val visibleValue = if(visible){
            View.VISIBLE
        }else{
            View.INVISIBLE
        }
        extractBinding.resultImageView1.visibility = visibleValue
        extractBinding.resultImageView2.visibility = visibleValue
        extractBinding.resultImageView3.visibility = visibleValue
        extractBinding.resultImageView4.visibility = visibleValue
        extractBinding.resultImageView5.visibility = visibleValue
        extractBinding.resultImageView6.visibility = visibleValue
    }

    companion object{
        private var INSTANCE : ExtractFragment ?= null
        fun newInstance() : ExtractFragment {
            if(INSTANCE == null) INSTANCE = ExtractFragment()

            return INSTANCE as ExtractFragment
        }
    }

}
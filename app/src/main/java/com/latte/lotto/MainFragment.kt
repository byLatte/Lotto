package com.latte.lotto

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import com.latte.lotto.databinding.FragmentMainBinding
import com.latte.lotto.extract.ExtractFragment
import com.latte.lotto.history.NumberHistoryFragment
import com.latte.lotto.win.WinFragment

private const val TAG = "MainFragment"

class MainFragment : Fragment() {

    private lateinit var mainBinding: FragmentMainBinding
    private lateinit var backPressedCallback: OnBackPressedCallback

    private var isDouble = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"Main Fragment")
     }
    //Context 객체 참조를 callback 속성에 저장
    override fun onAttach(context: Context) {
        super.onAttach(context)
        backPressedCallback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {

                if(isDouble){
                    activity?.finish()
                }else{
                    isDouble = true
                    Toast.makeText(context,"종료를 원하시면 뒤로가기 버튼을 다시 한번 눌러주세요",Toast.LENGTH_SHORT).show()
                    Handler(Looper.getMainLooper()).postDelayed({
                        isDouble = false
                    },2000)
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this,backPressedCallback)
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // fragment binding
        mainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container,false)
        val main = activity as MainActivity

        // 추출하기
        mainBinding.extractActivityButton.setOnClickListener {
            main.replaceFragment(ExtractFragment.newInstance())
        }
        // 기록보기
        mainBinding.recordActivityButton.setOnClickListener {
            main.replaceFragment(NumberHistoryFragment.newInstance())
        }
        // 당첨번호이력
        mainBinding.winHistoryButton.setOnClickListener {
            main.replaceFragment(WinFragment.newInstance())
        }


        return mainBinding.root
    }

    companion object{
        private var INSTANCE : MainFragment ?= null
        fun newInstance() : MainFragment {
            if(INSTANCE == null) INSTANCE = MainFragment()

            return INSTANCE as MainFragment
        }

    }

}
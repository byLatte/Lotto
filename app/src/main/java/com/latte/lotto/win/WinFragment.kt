package com.latte.lotto.win

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.latte.lotto.MainActivity
import com.latte.lotto.R
import com.latte.lotto.databinding.FragmentWinBinding

class WinFragment : Fragment() {

    private lateinit var binding : FragmentWinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_win,container,false)
        val main = activity as MainActivity
        binding.mainActivityButton.setOnClickListener {
            main.toMainFragment(WinFragment.toString())
        }
        return binding.root
    }

    companion object {
        private var INSTANCE : WinFragment?= null
        fun newInstance() : WinFragment {
            if(INSTANCE == null) INSTANCE = WinFragment()

            return INSTANCE as WinFragment
        }
    }
}
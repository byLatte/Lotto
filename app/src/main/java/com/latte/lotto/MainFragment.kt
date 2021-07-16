package com.latte.lotto

import android.content.Context
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.latte.lotto.databinding.FragmentMainBinding

private const val TAG = "MainFragment"

class MainFragment : Fragment() {

    private lateinit var mainBinding: FragmentMainBinding

    interface Callbacks{
        fun mainToExtract()
        fun mainToRecord()
    }

    private var callbacks: Callbacks? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     }
    //Context 객체 참조를 callback 속성에 저장
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val view = inflater.inflate(R.layout.fragment_main, container, false)
        // fragment binding
        mainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container,false)

        mainBinding.extractActivityButton.setOnClickListener {
            callbacks?.mainToExtract()
//            it.findNavController().navigate(R.id.action_mainFragment_to_extractFragment)
        }
        mainBinding.recordActivityButton.setOnClickListener {
            callbacks?.mainToRecord()
//            it.findNavController().navigate(R.id.action_mainFragment_to_recordFragment)
        }

        return mainBinding.root
    }

    companion object{
        fun newInstance(): MainFragment{
            return MainFragment()
        }

    }

}
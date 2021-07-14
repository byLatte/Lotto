package com.latte.lotto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.latte.lotto.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private lateinit var mainBinding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val view = inflater.inflate(R.layout.fragment_main, container, false)
        // fragment binding
        mainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container,false)

        mainBinding.extractActivityButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_extractFragment)
        }
        mainBinding.recordActivityButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_recordFragment)
        }

        return mainBinding.root
    }

}
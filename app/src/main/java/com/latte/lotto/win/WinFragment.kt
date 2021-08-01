package com.latte.lotto.win

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.latte.lotto.MainActivity
import com.latte.lotto.NumberModel
import com.latte.lotto.R
import com.latte.lotto.databinding.FragmentWinBinding

private const val TAG = "WinFragment"

class WinFragment : Fragment() {

    private lateinit var binding: FragmentWinBinding
    private var curDrwNo: Int = 0

    private val winViewModel: WinViewModel by lazy{
        ViewModelProvider(this).get(WinViewModel::class.java)
    }

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

        // 최근 회차
        curDrwNo = winViewModel.getCurDrwNo()

        val drwNos = mutableListOf<Int>()
        for(i: Int in 0..20){
            drwNos.add(curDrwNo-i)
        }

        val spinner: Spinner = binding.drwNoSpinner

        spinner.adapter = ArrayAdapter(requireContext(),R.layout.win_spinner_item,drwNos)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                setWinInfo(spinner.getItemAtPosition(position) as Int)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        setWinInfo(curDrwNo)

        return binding.root
    }

    private fun setWinInfo(drwNo: Int){
        val winInfo: LiveData<WinItem> = winViewModel.getWinInfo(drwNo)
        //번호 Res 정보
        val numberModel: NumberModel = NumberModel.get()
        val numberData: MutableList<Int> = numberModel.getNumberDataModel()

        winInfo.observe(
            viewLifecycleOwner,
            Observer { item ->
                binding.dateTextView.text = item.drwNoDate
                binding.drwtNoImageView1.setImageResource(numberData[item.drwtNo1-1])
                binding.drwtNoImageView2.setImageResource(numberData[item.drwtNo2-1])
                binding.drwtNoImageView3.setImageResource(numberData[item.drwtNo3-1])
                binding.drwtNoImageView4.setImageResource(numberData[item.drwtNo4-1])
                binding.drwtNoImageView5.setImageResource(numberData[item.drwtNo5-1])
                binding.drwtNoImageView6.setImageResource(numberData[item.drwtNo6-1])
                binding.bnusNoImageView.setImageResource(numberData[item.bnusNo-1])

            }
        )
    }


    companion object {
        private var INSTANCE : WinFragment?= null
        fun newInstance() : WinFragment {
            if(INSTANCE == null) INSTANCE = WinFragment()

            return INSTANCE as WinFragment
        }
    }
}
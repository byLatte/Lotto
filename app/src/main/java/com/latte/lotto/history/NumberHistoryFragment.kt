package com.latte.lotto.history

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.latte.lotto.MainActivity
import com.latte.lotto.R
import com.latte.lotto.database.NumberHistory
import com.latte.lotto.databinding.FragmentNumberHistoryBinding

private const val TAG = "NumberHistoryFragment"

class NumberHistoryFragment : Fragment() {

    private lateinit var binding : FragmentNumberHistoryBinding
    private lateinit var recyclerView: RecyclerView
    private var adapter : NumberHistoryRecyclerAdapter? = NumberHistoryRecyclerAdapter(emptyList())

    private var callback : Callbacks?= null

    interface Callbacks {
        fun removeDialog()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as Callbacks
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    private val numberHistoryViewModel: NumberHistoryViewModel by lazy{
        ViewModelProvider(this).get(NumberHistoryViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_number_history,container,false)
        recyclerView = binding.numberHistoryRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        val main = activity as MainActivity

        binding.mainActivityButton.setOnClickListener {
            main.toMainFragment(NumberHistoryFragment.toString())
        }
        //전체삭제
        binding.historyRemoveButton.setOnClickListener {
            callback?.removeDialog()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        numberHistoryViewModel.numberHistory.observe(
            viewLifecycleOwner,
            Observer { numbers ->
                numbers?.let{
                    updateUI(numbers)
                }
            }
        )
    }

    private fun updateUI(numbers: List<NumberHistory>){
        adapter = NumberHistoryRecyclerAdapter(numbers)
        if(adapter?.itemCount!! > 0) {
            binding.emptyTextView.visibility = View.INVISIBLE
        }else {
            binding.emptyTextView.visibility = View.VISIBLE
        }

        recyclerView.adapter = adapter
    }

    companion object {
        private var INSTANCE : NumberHistoryFragment?= null
        fun newInstance() : NumberHistoryFragment {
            if(INSTANCE == null) INSTANCE = NumberHistoryFragment()

            return INSTANCE as NumberHistoryFragment
        }
    }
}
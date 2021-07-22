package com.latte.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.latte.lotto.extract.ExtractFragment
import com.latte.lotto.history.NumberHistoryFragment
import com.latte.lotto.history.NumberHistoryRemoveDialog

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), MainFragment.Callbacks, ExtractFragment.Callbacks, NumberHistoryFragment.Callbacks {

    private lateinit var mAdView : AdView
    private var backDouble: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)

        if(currentFragment == null){
            val fragment = MainFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView,fragment)
                .commit()
        }

        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    private fun toMainFragment(fragmentName: String){
        val fragment = MainFragment.newInstance()
        supportFragmentManager.popBackStack(fragmentName,FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView,fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun mainToExtract() {
        val fragment = ExtractFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView,fragment)
            .addToBackStack("EXTRACT")
            .commit()
    }

    override fun mainToHistory() {
        val fragment = NumberHistoryFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView,fragment)
            .addToBackStack("HISTORY")
            .commit()
    }

    override fun removeDialog() {
        NumberHistoryRemoveDialog().show(supportFragmentManager,TAG)
    }

    //main fragment로 변경
    override fun extractToMain() {toMainFragment("EXTRACT")}

    override fun historyToMain() {toMainFragment("HISTORY")}


}
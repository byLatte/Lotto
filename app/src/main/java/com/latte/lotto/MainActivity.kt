package com.latte.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.latte.lotto.extract.ExtractFragment
import com.latte.lotto.extract.ExtractLoadingDialog
import com.latte.lotto.history.NumberHistoryFragment
import com.latte.lotto.history.NumberHistoryRemoveDialog

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), ExtractFragment.Callbacks, NumberHistoryFragment.Callbacks {

    private lateinit var mAdView : AdView


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

    private val dialog = ExtractLoadingDialog()
    override fun extractDialogShow() {
        dialog.show(supportFragmentManager,TAG)
    }

    override fun extractDialogDismiss() {
        dialog.dismiss()
    }

    override fun removeDialog() {
        NumberHistoryRemoveDialog().show(supportFragmentManager,TAG)
    }

    //프래그먼트 변경
    fun replaceFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView,fragment)
            .addToBackStack(fragment.toString())
            .commit()
    }

    // 메인으로 복귀
    fun toMainFragment(fragmentName: String){
        val fragment = MainFragment.newInstance()
        supportFragmentManager.popBackStack(fragmentName,FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView,fragment)
            .addToBackStack(null)
            .commit()
    }
}
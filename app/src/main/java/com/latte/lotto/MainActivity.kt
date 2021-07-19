package com.latte.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.latte.lotto.extract.ExtractFragment
import com.latte.lotto.history.NumberHistoryFragment

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), MainFragment.Callbacks, ExtractFragment.ExtractCallbacks {

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

    // fragment 에서 문제점 발생.. 수정필요.
    override fun onBackPressed() {
        if(backDouble){
            super.onBackPressed()
        }else{
            Toast.makeText(this,"뒤로 버튼을 한번 더 터치하시면 종료됩니다.",Toast.LENGTH_SHORT).show()
       }

        backDouble = true
        Handler().postDelayed({
            backDouble = false
        },2000)
    }

    override fun mainToExtract() {
        val fragment = ExtractFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView,fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun mainToHistory() {
        val fragment = NumberHistoryFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView,fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun extractToMain() {
        val fragment = MainFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView,fragment)
            .addToBackStack(null)
            .commit()
    }


}
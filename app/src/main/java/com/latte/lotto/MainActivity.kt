package com.latte.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.latte.lotto.databinding.ActivityMainBinding
import com.latte.lotto.extract.ExtractActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this , R.layout.activity_main)

        //번호 뽑기 화면이동
        mainBinding.extractActivityButton.setOnClickListener {
            val intent = Intent(applicationContext,ExtractActivity::class.java)
            startActivity(intent)
        }
    }
}
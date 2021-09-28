package com.dullyoung.androidtest1.view.acvitity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.dullyoung.androidtest1.R
import com.dullyoung.androidtest1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.stvSingapore.setOnClickListener {
            CarDetailActivity.start(this, CarDetailActivity.CountryType.Singapore);
        }
        binding.stvThailand.setOnClickListener {
            CarDetailActivity.start(this, CarDetailActivity.CountryType.Thailand);
        }
    }
}
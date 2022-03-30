package com.mvvm.audioplayer.ui.home.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mvvm.audioplayer.R
import com.mvvm.audioplayer.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
  lateinit  var activityHomeBinding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding!!.root)
        activityHomeBinding.tvMohan.text="Nagar"
    }
}
package com.mvvm.audioplayer.ui.player.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mvvm.audioplayer.R

class PlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
    }
}
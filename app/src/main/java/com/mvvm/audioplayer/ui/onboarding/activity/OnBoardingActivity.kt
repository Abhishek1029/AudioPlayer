package com.mvvm.audioplayer.ui.onboarding.activity

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.mvvm.audioplayer.R
import com.mvvm.audioplayer.data.SliderImageDataSource
import com.mvvm.audioplayer.data.SliderModel
import com.mvvm.audioplayer.databinding.ActivityOnBoardingBinding
import com.mvvm.audioplayer.ui.home.activity.HomeActivity
import com.mvvm.audioplayer.ui.onboarding.adapter.OnBoardingPagerAdapter
import java.util.*

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityOnBoardingBinding
    private lateinit var onBoardingPagerAdapter: OnBoardingPagerAdapter
    private var currentItem = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_on_boarding)

        val sliderModel = SliderModel(
            SliderImageDataSource.getSliderImages(),
            SliderImageDataSource.getHeadingText(),
            SliderImageDataSource.getSubHeadingText()
        )
        val sliderModelList = listOf(sliderModel)
        onBoardingPagerAdapter = OnBoardingPagerAdapter(this, sliderModelList)
        mBinding.onBoardingViewPager.apply {
            adapter = onBoardingPagerAdapter
            registerOnPageChangeCallback(onPageChangeCallback)
        }

        //swipeViewPagerAutomatically()
        bindContent()
    }

    private fun bindContent() {
        mBinding.skipBtn.setOnClickListener {
            requestPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Log.i("DEBUG", "permission denied")
            }
        }

    private fun swipeViewPagerAutomatically() {

        val handler = Handler(Looper.getMainLooper())
        val runnable = Runnable {
            if (mBinding.onBoardingViewPager.currentItem == SliderImageDataSource.getSliderImages().size - 1) {
                currentItem = 0
            }
            mBinding.onBoardingViewPager.currentItem = currentItem++
        }
        /**
         * timer for auto swiping
         */
        Timer().schedule(object : TimerTask() {
            override fun run() {
                handler.post(runnable)
            }

        }, 2000, 2000)
    }

    private val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            Log.e(TAG, "onPageSelected: $position")
        }
    }

    companion object {
        private const val TAG = "OnBoardingActivity"
    }
}
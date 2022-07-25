package com.mvvm.audioplayer.data

import com.mvvm.audioplayer.R

object SliderImageDataSource {
    fun getSliderImages() = listOf(
            R.drawable.slider1,
            R.drawable.slider2,
            R.drawable.slider3
        )

    fun getHeadingText() = listOf(
            R.string.welcome_to_audio_player,
            R.string.redefine_music,
            R.string.explore_the_feature
        )

    fun getSubHeadingText() = listOf(
        R.string.welcome_to_audio_player,
        R.string.redefine_music,
        R.string.explore_the_feature
    )
}
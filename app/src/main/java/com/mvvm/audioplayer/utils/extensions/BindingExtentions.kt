package com.mvvm.audioplayer.utils.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("loadImage")
fun ImageView.loadImage(image: Int) {
    this.setImageResource(image)
}
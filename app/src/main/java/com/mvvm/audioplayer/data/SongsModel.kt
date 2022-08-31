package com.mvvm.audioplayer.data

import android.os.Parcelable
import android.provider.MediaStore
import kotlinx.parcelize.Parcelize

@Parcelize
data class SongsModel(
    val id: Long,
    var path: String,
    var artistId: String,
    var artistName: String,
    var albumId: String,
    var albumName: String,
    var songTitle: String
) : Parcelable

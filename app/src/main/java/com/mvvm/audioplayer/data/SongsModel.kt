package com.mvvm.audioplayer.data

import android.provider.MediaStore

data class SongsModel(
    var path: String,  var artistId: String,
    var artistName: String ,var albumId: String, var albumName: String
)

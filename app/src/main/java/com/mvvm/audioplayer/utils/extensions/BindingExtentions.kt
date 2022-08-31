package com.mvvm.audioplayer.utils.extensions

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mvvm.audioplayer.R
import com.mvvm.audioplayer.data.SongsModel
import com.mvvm.audioplayer.utils.AudioUtils
import com.mvvm.audioplayer.utils.helper.Helper
import java.io.File

@BindingAdapter("loadImage")
fun ImageView.loadImage(image: Int) {
    this.setImageResource(image)
}

@BindingAdapter("loadSongsThumbnail")
fun ImageView.loadSongsThumbnail(songsModel: SongsModel) {
    Glide.with(this.context)
        .asBitmap()
        .load(AudioUtils.getUriFromAlbumId(songsModel.albumId.toLong()))
        .into(this)
}

@BindingAdapter("filePath")
fun TextView.filePath(path: String) {
    val file = File(path)
    this.text = AudioUtils.createTextWithTitle(
        this.context,
        R.string.file_path,
        file.absolutePath
    )
}

@BindingAdapter("fileName")
fun TextView.fileName(path: String) {
    val file = File(path)
    this.text = AudioUtils.createTextWithTitle(
        this.context,
        R.string.file_name,
        file.name
    )
}

@BindingAdapter("fileSize")
fun TextView.fileSize(path: String) {
    val file = File(path)
    this.text = AudioUtils.createTextWithTitle(
        this.context,
        R.string.file_size,
        AudioUtils.getFileSizeInString(file.length())
    )
}

/*@BindingAdapter("trackLength")
fun TextView.trackLength(path: String) {
    val file = File(path)
    this.text = AudioUtils.createTextWithTitle(
        this.context,
        R.string.file_size,
        file.
    )
}*/

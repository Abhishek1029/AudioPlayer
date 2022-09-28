package com.mvvm.audioplayer.utils

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.text.Spanned
import androidx.core.text.HtmlCompat
import org.jaudiotagger.audio.AudioFileIO
import org.jaudiotagger.audio.AudioHeader
import java.io.File

object AudioUtils {
    fun getUriFromAlbumId(albumId: Long): Uri {
        val sArtworkUri = Uri.parse("content://media/external/audio/albumart")
        return ContentUris.withAppendedId(sArtworkUri, albumId)
    }


    fun createTextWithTitle(context: Context, titleResId: Int, title: String): Spanned {
        return HtmlCompat.fromHtml(
            "<b>" + context.resources.getString(titleResId) + ": " + "</b>" + title,
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
    }

    fun getFileSizeInString(sizeInBytes: Long): String {
        val sizeInKbs = sizeInBytes / 1024;
        val sizeInMbs = sizeInKbs / 1024
        return "$sizeInMbs MB"
    }

    fun getAudioHeader(file: File): AudioHeader {
        val audioFile = AudioFileIO.read(file)
        return audioFile.audioHeader
    }

    fun getSongUri(id: Long?): Uri? {
            return id?.let {
                ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    it
                )
            }

    }
}
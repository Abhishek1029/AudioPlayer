package com.mvvm.audioplayer.ui.home.repository

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mvvm.audioplayer.data.SongsModel
import com.mvvm.audioplayer.utils.extensions.getString
import javax.inject.Inject


class SongsRepository @Inject constructor(var context: Context) {
    private var songsArrayLists: ArrayList<SongsModel>? = null
    suspend fun getSongsList(): ArrayList<SongsModel> {
        songsArrayLists = ArrayList()
        val uri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val arr = arrayOf(
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.ARTIST_ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.TITLE
        )

        val cursor: Cursor? = context.contentResolver.query(uri, arr, null, null, null)
        while (cursor?.moveToNext()!!) {
            val songsModel = SongsModel(
                cursor.getString(MediaStore.Audio.AudioColumns.DATA),
                cursor.getString(MediaStore.Audio.AudioColumns.ARTIST_ID),
                cursor.getString(MediaStore.Audio.AudioColumns.ARTIST),
                cursor.getString(MediaStore.Audio.AudioColumns.ALBUM_ID),
                cursor.getString(MediaStore.Audio.AudioColumns.ALBUM),
                cursor.getString(MediaStore.Audio.AudioColumns.TITLE)
            )
            songsArrayLists?.add(songsModel)
        }
        Log.e(TAG, "getSongsList: ${songsArrayLists?.size}" )
        return songsArrayLists!!
    }

    companion object{
        private const val TAG = "SongsRepository"
    }
}
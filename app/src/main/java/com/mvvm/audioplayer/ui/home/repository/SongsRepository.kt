package com.mvvm.audioplayer.ui.home.repository

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mvvm.audioplayer.data.SongsModel
import com.mvvm.audioplayer.utils.extensions.getLong
import com.mvvm.audioplayer.utils.extensions.getString
import javax.inject.Inject


class SongsRepository @Inject constructor(var context: Context) {
    private var songsArrayLists: ArrayList<SongsModel>? = null
    suspend fun getSongsList(cursor:Cursor?): ArrayList<SongsModel> {
        songsArrayLists = ArrayList()

        while (cursor?.moveToNext()!!) {
            val songsModel = SongsModel(
                cursor.getLong(BaseColumns._ID),
                cursor.getString(MediaStore.Audio.AudioColumns.DATA),
                cursor.getString(MediaStore.Audio.AudioColumns.ARTIST_ID),
                cursor.getString(MediaStore.Audio.AudioColumns.ARTIST),
                cursor.getString(MediaStore.Audio.AudioColumns.ALBUM_ID),
                cursor.getString(MediaStore.Audio.AudioColumns.ALBUM),
                cursor.getString(MediaStore.Audio.AudioColumns.TITLE)
            )
            songsArrayLists?.add(songsModel)
        }
        Log.e(TAG, "getSongsList: ${songsArrayLists?.size}")
        return songsArrayLists!!
    }

    fun getSongCursor(uri: Uri, projection: Array<String>, sortOrder: String?): Cursor? {
        return context.contentResolver.query(uri, projection, null, null, sortOrder)
    }

    companion object {
        private const val TAG = "SongsRepository"
    }
}
package com.mvvm.audioplayer.ui.home.repository

import android.content.ContentProvider
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm.audioplayer.data.SongsModel

class SongsRepository(var context:Context) {

    val songslist:MutableLiveData<ArrayList<SongsModel>> =MutableLiveData()

    fun getSongsList():MutableLiveData<ArrayList<SongsModel>>{
        val uri:Uri=MediaStore.Audio.Media.EXTERNAL_CONTENT_URI  ///////////content:// return uri
        val arr = arrayOf(MediaStore.Audio.Media.DATA,MediaStore.Audio.Media.ARTIST_ID,MediaStore.Audio.Media.ARTIST,MediaStore.Audio.Media.ALBUM_ID,MediaStore.Audio.Media.ALBUM)   //////

        val cursor:Cursor?= context.contentResolver.query(uri,arr,null,null,null)
        while (cursor?.moveToNext()!!)
        {
            val songsModel:SongsModel =SongsModel( cursor.getColumnName(0), cursor.getColumnName(1), cursor.getColumnName(2), cursor.getColumnName(3), cursor.getColumnName(4))
           Log.d("prince",""+songsModel.toString())
        }


        return songslist
    }
}
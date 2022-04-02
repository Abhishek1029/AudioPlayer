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

    private val songslist:MutableLiveData<ArrayList<SongsModel>> =MutableLiveData()
    private val songsArrayLits:ArrayList<SongsModel> =ArrayList()
    fun getSongsList():MutableLiveData<ArrayList<SongsModel>>{
        val uri:Uri=MediaStore.Audio.Media.EXTERNAL_CONTENT_URI  ///////////content:// return uri
        val arr = arrayOf(MediaStore.Audio.Media.DATA,MediaStore.Audio.Media.ARTIST_ID,MediaStore.Audio.Media.ARTIST,MediaStore.Audio.Media.ALBUM_ID,MediaStore.Audio.Media.ALBUM,MediaStore.Audio.Media.TITLE)   //////

        val cursor:Cursor?= context.contentResolver.query(uri,arr,null,null,null)
        while (cursor?.moveToNext()!!)
        {
            val songsModel =SongsModel( cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getString(5))
            songsArrayLits.add(songsModel)
        }
        if (songsArrayLits.size>0)
        {
            songslist.value =songsArrayLits
        }


        return songslist
    }
}
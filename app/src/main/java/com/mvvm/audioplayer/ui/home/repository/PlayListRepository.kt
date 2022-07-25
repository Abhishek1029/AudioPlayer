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
import com.mvvm.audioplayer.roomdb.AudioPlayerDB
import com.mvvm.audioplayer.roomdb.PlaylistEntity
import com.mvvm.audioplayer.utils.extensions.getString

class PlayListRepository(var context: Context, private val database: AudioPlayerDB) {

    var playlistItme=MutableLiveData<List<PlaylistEntity>>()

    fun addPlayListItem()
    {

     //   val roomDatabase =database.playlistDao().addPlaylist()

    }

     suspend fun readPlaylistItem():MutableLiveData<List<PlaylistEntity>>
    {
        playlistItme.postValue(database.playlistDao().getPlayList())
        return playlistItme
    }
}
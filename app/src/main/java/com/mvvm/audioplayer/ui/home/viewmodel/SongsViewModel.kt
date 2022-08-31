package com.mvvm.audioplayer.ui.home.viewmodel

import android.provider.BaseColumns
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.audioplayer.data.SongsModel
import com.mvvm.audioplayer.ui.home.repository.SongsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "SongsViewModel"

@HiltViewModel
class SongsViewModel @Inject constructor(val respo: SongsRepository) : ViewModel() {

    var songslist: MutableLiveData<ArrayList<SongsModel>> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        Log.e(TAG, "onCleared: executed")
    }

    suspend fun getSongsList() {
        songslist.postValue(
            respo.getSongsList(
                respo.getSongCursor(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    arrayOf(
                        MediaStore.Audio.Media.DATA,
                        MediaStore.Audio.Media.ARTIST_ID,
                        MediaStore.Audio.Media.ARTIST,
                        MediaStore.Audio.Media.ALBUM_ID,
                        MediaStore.Audio.Media.ALBUM,
                        MediaStore.Audio.Media.TITLE,
                        BaseColumns._ID
                    ),
                    null
                )
            )
        )
    }
}
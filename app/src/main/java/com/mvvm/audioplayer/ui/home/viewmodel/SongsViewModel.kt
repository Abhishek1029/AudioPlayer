package com.mvvm.audioplayer.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvm.audioplayer.data.SongsModel
import com.mvvm.audioplayer.ui.home.repository.SongsRepository

private const val TAG = "SongsViewModel"

class SongsViewModel(var respo:SongsRepository) : ViewModel() {

    var songslist:MutableLiveData<ArrayList<SongsModel>> = MutableLiveData()



    override fun onCleared() {
        super.onCleared()
        Log.e(TAG, "onCleared: executed")
    }
    fun getSongsList():MutableLiveData<ArrayList<SongsModel>>
    {
        songslist =respo.getSongsList()

        return songslist
    }
}
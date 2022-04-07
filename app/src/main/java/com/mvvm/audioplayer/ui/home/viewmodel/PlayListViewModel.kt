package com.mvvm.audioplayer.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvm.audioplayer.data.SongsModel
import com.mvvm.audioplayer.ui.home.repository.PlayListRepository
import com.mvvm.audioplayer.ui.home.repository.SongsRepository

class PlayListViewModel(var repository: PlayListRepository): ViewModel() {

    fun getPlayList()
    {
        repository.
    }

    fun addPlaylist()
    {
        repository.addPlayListItem()
    }

    override fun onCleared() {
        super.onCleared()
    }
}
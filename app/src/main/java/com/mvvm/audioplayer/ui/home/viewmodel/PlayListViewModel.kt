package com.mvvm.audioplayer.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.audioplayer.data.SongsModel
import com.mvvm.audioplayer.roomdb.PlaylistEntity
import com.mvvm.audioplayer.ui.home.repository.PlayListRepository
import com.mvvm.audioplayer.ui.home.repository.SongsRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayListViewModel(var repository: PlayListRepository) : ViewModel() {

    var playlistdata = MutableLiveData<List<PlaylistEntity>>()

    fun getPlayList() {
        val exceHandler = CoroutineExceptionHandler{ context,exception ->
            println("Exception aa gya bhai:- ${exception.localizedMessage}")
        }
        viewModelScope.launch(exceHandler) {
            println("Hello ${5/0}")
            println("Print ni hoga ya hoga suspense")
        }
       /* viewModelScope.launch {
            playlistdata = repository.readPlaylistItem()
        }*/
    }

    /*  fun addPlaylist()
      {
          playlistdata =repository.addPlayListItem()
      }*/

    override fun onCleared() {
        super.onCleared()
    }
}
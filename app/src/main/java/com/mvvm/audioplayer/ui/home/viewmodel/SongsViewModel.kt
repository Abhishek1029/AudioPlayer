package com.mvvm.audioplayer.ui.home.viewmodel

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
class SongsViewModel @Inject constructor(val respo:SongsRepository):ViewModel(){

    var songslist:MutableLiveData<ArrayList<SongsModel>> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        Log.e(TAG, "onCleared: executed")
    }
  suspend  fun getSongsList() {
            Log.e(TAG, "getSongsList: ${respo.getSongsList().size}",)
            songslist.postValue(respo.getSongsList())
    }
}
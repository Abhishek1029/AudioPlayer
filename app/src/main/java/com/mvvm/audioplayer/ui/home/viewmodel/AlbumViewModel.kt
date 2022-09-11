package com.mvvm.audioplayer.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvm.audioplayer.data.Album
import com.mvvm.audioplayer.ui.home.repository.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(private val albumRepository: AlbumRepository) : ViewModel() {
    private var _albumList: MutableLiveData<List<Album>> = MutableLiveData()
    val albumList get() = _albumList
    fun getAlbumList() {
        CoroutineScope(Dispatchers.IO).launch {
            val listOfAlbums = albumRepository.albumList()
            withContext(Dispatchers.Main) {
                _albumList.value = listOfAlbums
            }
        }
    }
}
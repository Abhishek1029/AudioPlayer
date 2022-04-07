package com.mvvm.audioplayer.ui.home.provider

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvvm.audioplayer.ui.home.repository.PlayListRepository
import com.mvvm.audioplayer.ui.home.repository.SongsRepository
import com.mvvm.audioplayer.ui.home.viewmodel.PlayListViewModel
import com.mvvm.audioplayer.ui.home.viewmodel.SongsViewModel

class PlaylistFactory(var repo:PlayListRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return PlayListViewModel(repo) as T
    }
}
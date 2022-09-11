package com.mvvm.audioplayer.data

import com.mvvm.audioplayer.data.SongsModel

data class Album(
    val id:String,
    val songs: List<SongsModel>,
    val startSongs: SongsModel = songs.firstOrNull() ?: SongsModel(
        id = -1,
        path = "",
        artistId = "",
        artistName = "",
        albumId = "",
        albumName = "",
        songTitle = ""
    ),
    val albumTitle: String = startSongs.albumName,
    val songsCount: Int = songs.size
)

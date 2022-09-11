package com.mvvm.audioplayer.ui.home.repository

import android.provider.BaseColumns
import android.provider.MediaStore
import com.mvvm.audioplayer.data.Album
import com.mvvm.audioplayer.data.SongsModel

interface AlbumRepository {
    suspend fun albumList(): List<Album>
}

class AlbumRepositoryImpl(private val songsRepository: SongsRepository) : AlbumRepository {
    override suspend fun albumList(): List<Album> {
        return getAlbums(getSongsList())
    }

    private fun getAlbums(songs: List<SongsModel>): List<Album> {
        return songs.groupBy { it.albumId }.map { Album(it.key, it.value) }
    }

    private suspend fun getSongsList(): List<SongsModel> {
        return songsRepository.getSongsList(
            songsRepository.getSongCursor(
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
            ))
    }

}

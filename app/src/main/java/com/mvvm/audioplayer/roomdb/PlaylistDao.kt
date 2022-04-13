package com.mvvm.audioplayer.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao()
interface PlaylistDao {
      @Query("Select * from playlist")
      suspend fun getPlayList():List<PlaylistEntity>

      @Insert(onConflict = IGNORE)
      suspend fun addPlaylist(playlistEntity: PlaylistEntity)
}
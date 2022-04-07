package com.mvvm.audioplayer.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao()
interface PlaylistDao {

//      var a:Int
      @Query("Select * from playlist")
      fun getPlayList():List<PlaylistEntity>

      @Insert(onConflict = IGNORE)
      fun addPlaylist(playlistEntity: PlaylistEntity)
}
package com.mvvm.audioplayer.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query

@Dao
interface CreatePlaylistDao {
    @Insert(onConflict = IGNORE)
    fun insertAll(createPlaylist:CreatePlaylistEntity)

    @Query("SELECT * from CreatePlaylist")
    fun getAllPlaylist():LiveData<List<CreatePlaylistEntity>>

    @Delete
    fun deleteAllPlaylist()
}
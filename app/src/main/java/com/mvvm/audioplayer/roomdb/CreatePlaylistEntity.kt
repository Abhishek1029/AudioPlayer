package com.mvvm.audioplayer.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CreatePlaylist")
class CreatePlaylistEntity(
    @PrimaryKey()
    @ColumnInfo(name = "song_id")
    val id: Int=0,
    @ColumnInfo(name = "song_name")
    val name: String,

)
package com.mvvm.audioplayer.roomdb

import androidx.room.*

@Entity(tableName = "Playlist")
data class PlaylistEntity(
 @PrimaryKey
 val autoid:Int=0,
@ColumnInfo (name = "path")
val path:String,
 @ColumnInfo (name = "playlistname")
 val playlistname:String,
 @ColumnInfo (name = "artistid")
 val artistid:String,
 @ColumnInfo (name = "albumname")
 val albumname:String,
 @ColumnInfo (name = "image")
 val image:String,
 @ColumnInfo (name = "artistname")
 val artistname:String,

 @TypeConverters(DateTypeConverter::class)
 @ColumnInfo(name = "timestamp")
  var timestamp:Int,

 val albumid:String)

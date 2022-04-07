package com.mvvm.audioplayer.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PlaylistEntity::class], version = 1)
abstract class AudioPlayerDB : RoomDatabase() {
    abstract fun playlistDao():PlaylistDao

    companion object{
        private var instance:AudioPlayerDB?=null
        fun getDatabase(context: Context):AudioPlayerDB{
            val tempInstance =instance
            if (tempInstance!=null)
            {
                return tempInstance
            }
            synchronized(this){
                val roomInstance =Room.databaseBuilder(context,AudioPlayerDB::class.java,"playlistdb").allowMainThreadQueries().build()
                instance =roomInstance
                return roomInstance
            }
        }
    }

}
package com.mvvm.audioplayer.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [PlaylistEntity::class,CreatePlaylistEntity::class], version = 2   )
@TypeConverters(DateTypeConverter::class)
abstract class AudioPlayerDB : RoomDatabase() {
    abstract fun playlistDao():PlaylistDao

    companion object{

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
           database.execSQL("ALTER TABLE Playlist ADD COLUMN  timestamp INTEGER  NOT NULL DEFAULT(0) ")
            }
        }

        private var instance:AudioPlayerDB?=null
        fun getDatabase(context: Context):AudioPlayerDB{
            val tempInstance =instance
            if (tempInstance!=null)
            {
                return tempInstance
            }
            synchronized(this){
                val roomInstance =Room.databaseBuilder(context,AudioPlayerDB::class.java,"playlistdb")
                    .addMigrations(MIGRATION_1_2).build()
                instance =roomInstance
                return roomInstance
            }
        }
    }

}
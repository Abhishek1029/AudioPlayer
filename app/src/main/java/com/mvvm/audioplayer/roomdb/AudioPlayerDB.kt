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
           database.execSQL("ALTER TABLE Playlist ADD COLUMN  timestamp INTEGER  NOT NULL DEFAULT(0) ");
//
//            database.execSQL("ALTER TABLE VideoDownload ADD COLUMN  course_id TEXT ");
//
//            database.execSQL("ALTER TABLE   UserHistroyTable ADD COLUMN  course_id TEXT ");
//            database.execSQL("ALTER TABLE   VideoTable ADD COLUMN  course_id TEXT ");
                //  database.execSQL("CREATE TABLE IF NOT EXISTS  `Test` ( `uid` INTEGER NOT NULL, `test_id` TEXT , `test_name` TEXT , `test_paid` TEXT , `test_image` TEXT , `test_cut_price` TEXT , `timstamp` TEXT , `test_normal_price` TEXT, PRIMARY KEY(`uid`))");
                //database.execSQL("ALTER TABLE user ADD COLUMN  is_active INTEGER  NOT NULL DEFAULT(0)");
                /*    database.execSQL("ALTER TABLE LiveClass ADD COLUMN  live_class_timstamp TEXT ");

            database.execSQL("UPDATE LiveClass set live_class_timstamp = timstamp");*/
                ////column chnage ///
                // database.execSQL("ALTER TABLE LiveClass  RENAME COLUMN liveclass_id_image TO liveclass_image");
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
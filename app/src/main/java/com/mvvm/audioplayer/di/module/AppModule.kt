package com.mvvm.audioplayer.di.module

import android.app.Application
import android.content.Context
import android.widget.ImageView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.mvvm.audioplayer.AudioPlayerApplication
import com.mvvm.audioplayer.roomdb.AudioPlayerDB
import com.mvvm.audioplayer.ui.home.repository.SongsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun providesRoomDb(context: Context): AudioPlayerDB
    {
        return Room.databaseBuilder(context,AudioPlayerDB::class.java,"playlistdb").build()
    }

    @Singleton
    @Provides
    fun provideApplicationContext(application: Application):Context
    {
      return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideSongsRepo(context: Context):SongsRepository
    {

        return SongsRepository(context)
    }
    @Singleton
    @Provides
    fun provideGlideimage(context: Context):RequestManager
    {
         return Glide.with(context)
    }
}
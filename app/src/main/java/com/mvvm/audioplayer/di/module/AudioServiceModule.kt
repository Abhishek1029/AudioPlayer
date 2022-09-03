package com.mvvm.audioplayer.di.module

import android.app.Application
import com.google.android.exoplayer2.ExoPlayer
import com.mvvm.audioplayer.AudioPlayerApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.scopes.ServiceScoped

@Module
@InstallIn(ServiceComponent::class)
object AudioServiceModule {

    @Provides
    @ServiceScoped
    fun providesExoPlayerInstance(application: Application): ExoPlayer =
        ExoPlayer.Builder(application.applicationContext)
            .build()
}
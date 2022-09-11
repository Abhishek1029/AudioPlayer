package com.mvvm.audioplayer.di.module

import com.mvvm.audioplayer.ui.home.repository.AlbumRepository
import com.mvvm.audioplayer.ui.home.repository.AlbumRepositoryImpl
import com.mvvm.audioplayer.ui.home.repository.SongsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun providesAlbumRepository(songsRepository: SongsRepository): AlbumRepository {
        return AlbumRepositoryImpl(songsRepository)
    }
}
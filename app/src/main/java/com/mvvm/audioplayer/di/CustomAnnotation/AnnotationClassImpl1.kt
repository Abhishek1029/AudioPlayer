package com.mvvm.audioplayer.di.CustomAnnotation

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AnnotationClassImpl1 {

    @Singleton
    @Provides
    @CustomeAnotation1
    fun provideString1():String{

        return "ABCD"
    }
    @Singleton
    @Provides
    @CustomeAnotation2
    fun provideString2():String{

        return "12345678"
    }


}
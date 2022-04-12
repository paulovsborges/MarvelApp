package com.pvsb.marvelapp.framework.di

import com.pvsb.marvelapp.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object BaseUrlModule {

    @BaseUrlInjection
    @Binds
    fun provideBaseUrl(): String = BuildConfig.BASE_URL
}
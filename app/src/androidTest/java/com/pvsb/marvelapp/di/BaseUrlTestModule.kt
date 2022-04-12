package com.pvsb.marvelapp.di

import com.pvsb.marvelapp.framework.di.BaseUrlInjection
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object BaseUrlTestModule {

    @BaseUrlInjection
    @Binds
    fun provideBaseUrl(): String = "http://localhost:8080/"
}
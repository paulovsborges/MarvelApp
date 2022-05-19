package com.pvsb.marvelapp.data.di

import com.pvsb.core.data.repository.CharactersRemoteDataSource
import com.pvsb.core.data.repository.CharactersRepository
import com.pvsb.marvelapp.data.network.model.DataWrapperResponse
import com.pvsb.marvelapp.data.network.remote.RemoteCharactersDataSource
import com.pvsb.marvelapp.data.network.repository.CharactersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun characterRepository(repository: CharactersRepositoryImpl): CharactersRepository

    @Binds
    fun remoteDataSource(
        datasource: RemoteCharactersDataSource
    ): CharactersRemoteDataSource<DataWrapperResponse>

}
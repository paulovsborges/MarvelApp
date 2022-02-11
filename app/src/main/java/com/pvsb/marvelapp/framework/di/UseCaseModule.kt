package com.pvsb.marvelapp.framework.di

import com.pvsb.core.usecase.CharactersUseCase
import com.pvsb.core.usecase.CharactersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun charactersUseCase(useCase: CharactersUseCaseImpl) : CharactersUseCase
}
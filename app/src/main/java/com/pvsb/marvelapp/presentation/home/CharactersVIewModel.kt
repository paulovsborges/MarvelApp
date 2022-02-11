package com.pvsb.marvelapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pvsb.core.domain.model.Character
import com.pvsb.core.usecase.CharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class CharactersVIewModel @Inject constructor(
    private val useCase: CharactersUseCase
) : ViewModel() {

    fun charactersPagingData(query: String): Flow<PagingData<Character>> = flow {
        useCase(
            CharactersUseCase.CharactersParams(
                query,
                getPagingConfig()
            )
        ).cachedIn(viewModelScope)
    }

    private fun getPagingConfig() = PagingConfig(pageSize = 20)
}
package com.pvsb.core.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pvsb.core.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharactersUseCase {

    operator fun invoke(params: CharactersParams): Flow<PagingData<Character>>

    data class CharactersParams(val query: String, val pagingConfig: PagingConfig)

}
package com.pvsb.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pvsb.core.data.repository.CharactersRepository
import com.pvsb.core.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharactersUseCase(
    private val charactersRepository: CharactersRepository
) : PagingUseCase<CharactersUseCase.CharactersParams, Character>() {

    data class CharactersParams(val query: String, val pagingConfig: PagingConfig)

    override fun createFlowObservable(params: CharactersParams): Flow<PagingData<Character>> =
        flow {
            Pager(config = params.pagingConfig) {
                charactersRepository.getCharacter(params.query)
            }
        }
}
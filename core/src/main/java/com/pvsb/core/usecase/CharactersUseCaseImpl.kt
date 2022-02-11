package com.pvsb.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingData
import com.pvsb.core.data.repository.CharactersRepository
import com.pvsb.core.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersUseCaseImpl @Inject constructor(
    private val charactersRepository: CharactersRepository
) : PagingUseCase<CharactersUseCase.CharactersParams, Character>(), CharactersUseCase {

    override fun createFlowObservable(params: CharactersUseCase.CharactersParams): Flow<PagingData<Character>> {
        val pagingSource = charactersRepository.getCharacter(params.query)
        return Pager(config = params.pagingConfig) {
            pagingSource
        }.flow
    }
}
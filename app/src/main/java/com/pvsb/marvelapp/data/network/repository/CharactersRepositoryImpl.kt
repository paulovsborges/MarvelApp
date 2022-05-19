package com.pvsb.marvelapp.data.network.repository

import androidx.paging.PagingSource
import com.pvsb.core.data.repository.CharactersRemoteDataSource
import com.pvsb.core.data.repository.CharactersRepository
import com.pvsb.core.domain.model.Character
import com.pvsb.marvelapp.data.network.model.DataWrapperResponse
import com.pvsb.marvelapp.data.paging.CharactersPagingSource
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteData: CharactersRemoteDataSource<DataWrapperResponse>
) : CharactersRepository {

    override fun getCharacter(query: String): PagingSource<Int, Character> {
        return CharactersPagingSource(remoteData, query)
    }
}
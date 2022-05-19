package com.pvsb.marvelapp.data.network.remote

import com.pvsb.core.data.repository.CharactersRemoteDataSource
import com.pvsb.marvelapp.data.network.model.DataWrapperResponse
import com.pvsb.marvelapp.data.network.service.MarvelService
import javax.inject.Inject

class RemoteCharactersDataSource @Inject constructor(
    private val service: MarvelService
) : CharactersRemoteDataSource<DataWrapperResponse> {

    override suspend fun fetchCharacters(queries: Map<String, String>): DataWrapperResponse {
        return service.getCharacters(queries)
    }
}
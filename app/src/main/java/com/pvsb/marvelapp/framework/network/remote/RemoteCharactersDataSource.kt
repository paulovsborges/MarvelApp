package com.pvsb.marvelapp.framework.network.remote

import com.pvsb.core.data.repository.CharactersRemoteDataSource
import com.pvsb.marvelapp.framework.network.model.DataWrapperResponse
import com.pvsb.marvelapp.framework.network.service.MarvelService
import javax.inject.Inject

class RemoteCharactersDataSource @Inject constructor(
    private val service: MarvelService
) : CharactersRemoteDataSource<DataWrapperResponse> {

    override suspend fun fetchCharacters(queries: Map<String, String>): DataWrapperResponse {
        return service.getCharacters(queries)
    }
}
package com.pvsb.core.data.repository

interface CharactersRemoteDataSource<T: Any> {

    suspend fun fetchCharacters(queries: Map<String, String>): T
}
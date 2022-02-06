package com.pvsb.core.data.network.service

import com.pvsb.core.data.network.model.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MarvelService {
    @GET("characters")
    suspend fun getCharacters(@QueryMap queries: Map<String, String>): DataWrapperResponse
}
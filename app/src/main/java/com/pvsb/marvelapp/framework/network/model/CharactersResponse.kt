package com.pvsb.marvelapp.framework.network.model

data class DataWrapperResponse(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: ContainerResponse
)

data class ContainerResponse(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<CharactersResponse>
)

data class CharactersResponse(
    val id: Int,
    val name: String,
    val thumbnail: ImagesResponse
)

data class ImagesResponse(
    val path: String,
    val extension: String
)

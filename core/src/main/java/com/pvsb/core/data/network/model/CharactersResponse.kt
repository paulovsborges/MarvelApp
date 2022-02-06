package com.pvsb.core.data.network.model

internal data class DataWrapperResponse(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: ContainerResponse
)

internal data class ContainerResponse(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<CharactersResponse>
)

internal data class CharactersResponse(
    val id: Int,
    val name: String,
    val thumbnail: ImagesResponse
)

internal data class ImagesResponse(
    val path: String,
    val extension: String
)

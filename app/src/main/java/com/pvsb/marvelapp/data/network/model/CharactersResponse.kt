package com.pvsb.marvelapp.data.network.model

import com.google.gson.annotations.SerializedName

data class DataWrapperResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("attributionText")
    val attributionText: String,
    @SerializedName("attributionHTML")
    val attributionHTML: String,
    @SerializedName("data")
    val data: ContainerResponse
)

data class ContainerResponse(
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("count")
    val count: Int,
    @SerializedName("results")
    val results: List<CharactersResponse>
)

data class CharactersResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("thumbnail")
    val thumbnail: ImagesResponse
)

data class ImagesResponse(
    @SerializedName("path")
    val path: String,
    @SerializedName("extension")
    val extension: String
)

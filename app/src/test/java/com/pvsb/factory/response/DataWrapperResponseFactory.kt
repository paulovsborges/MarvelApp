package com.pvsb.factory.response

import com.pvsb.marvelapp.framework.network.model.CharactersResponse
import com.pvsb.marvelapp.framework.network.model.ContainerResponse
import com.pvsb.marvelapp.framework.network.model.DataWrapperResponse
import com.pvsb.marvelapp.framework.network.model.ImagesResponse

class DataWrapperResponseFactory {

    fun create(): DataWrapperResponse {

        val imagesResponse = ImagesResponse("google.com", "jpg")
        val charactersResponse = CharactersResponse(1, "iron man", imagesResponse)
        val charactersResponse2 = CharactersResponse(1, "A-Bomb", imagesResponse)

        return DataWrapperResponse(
            1,
            "",
            "",
            "",
            "",
            ContainerResponse(
                0,
                20,
                20,
                20,
                listOf(
                    charactersResponse,
                    charactersResponse2
                )
            )
        )
    }
}
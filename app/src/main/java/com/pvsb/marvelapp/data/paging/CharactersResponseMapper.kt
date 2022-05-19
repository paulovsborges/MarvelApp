package com.pvsb.marvelapp.data.paging

import com.pvsb.core.domain.model.Character
import com.pvsb.marvelapp.data.network.model.CharactersResponse
import com.pvsb.marvelapp.data.network.model.ImagesResponse

object CharactersResponseMapper {

    fun toDomain(source: List<CharactersResponse>): List<Character> =
        source.map { character ->
            character.run {
                Character(
                    id = id,
                    name = name,
                    image = characterImage(thumbnail)
                )
            }
        }

    private fun characterImage(source: ImagesResponse): String {
        return "${source.path}/standard_amazing.${source.extension}".replace("http", "https")
    }
}
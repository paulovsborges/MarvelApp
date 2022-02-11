package com.pvsb.marvelapp.framework.paging

import com.pvsb.core.domain.model.Character
import com.pvsb.marvelapp.framework.network.model.CharactersResponse
import com.pvsb.marvelapp.framework.network.model.ImagesResponse

object CharactersResponseMapper {

    fun toDomain(source: List<CharactersResponse>): List<Character> =
        source.map { character ->
            character.run {
                Character(
                    name = name,
                    image = characterImage(thumbnail)
                )
            }
        }

    private fun characterImage(source: ImagesResponse): String {
        return "${source.path}/portrait_incredible.${source.extension}"
    }
}
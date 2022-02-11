package com.pvsb.core.data.repository

import androidx.paging.PagingSource
import com.pvsb.core.domain.model.Characters

interface CharacterRepository {

    fun getCharacter(query: String) : PagingSource<Int, Characters>
}
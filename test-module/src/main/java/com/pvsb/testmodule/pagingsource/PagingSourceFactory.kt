package com.pvsb.testmodule.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pvsb.core.domain.model.Character

class PagingSourceFactory {

    fun create(heroes: List<Character>) = object : PagingSource<Int, Character>(){

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
            return LoadResult.Page(
                heroes,
                null,
                20
            )
        }

        override fun getRefreshKey(state: PagingState<Int, Character>): Int {
            return 1
        }
    }
}
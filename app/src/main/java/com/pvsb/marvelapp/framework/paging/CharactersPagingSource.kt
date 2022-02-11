package com.pvsb.marvelapp.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pvsb.core.data.repository.CharactersRemoteDataSource
import com.pvsb.core.domain.model.Characters
import com.pvsb.marvelapp.framework.network.model.DataWrapperResponse

class CharactersPagingSource(
    private val remote: CharactersRemoteDataSource<DataWrapperResponse>,
    private val query: String
) : PagingSource<Int, Characters>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Characters> {
        return try {
            val offSet = params.key ?: 0
            val queries = hashMapOf(
                "offset" to offSet.toString()
            )

            if (query.isNotEmpty()) {
                queries["nameStartsWith"] = query
            }

            val response = remote.fetchCharacters(queries)
            val responseOffset = response.data.offset
            val totalCharacters = response.data.total

            val list = CharactersResponseMapper.toDomain(response.data.results)

            val nextKey = if (responseOffset < totalCharacters) {
                responseOffset + OFFSET
            } else {
                null
            }

            LoadResult.Page(
                data = list,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Characters>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(OFFSET) ?: anchorPage?.nextKey?.minus(OFFSET)
        }
    }

    private companion object {
        const val OFFSET = 20
    }
}
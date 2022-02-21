package com.pvsb.mockito.marvelapp.framework.paging

import androidx.paging.PagingSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.pvsb.core.data.repository.CharactersRemoteDataSource
import com.pvsb.core.domain.model.Character
import com.pvsb.factory.response.DataWrapperResponseFactory
import com.pvsb.marvelapp.framework.network.model.DataWrapperResponse
import com.pvsb.marvelapp.framework.paging.CharactersPagingSource
import com.pvsb.testmodule.CoroutineRule
import com.pvsb.testmodule.model.CharacterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharactersPagingSourceTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    private lateinit var pagingSource: CharactersPagingSource

    @Mock
    private lateinit var remoteDataSource: CharactersRemoteDataSource<DataWrapperResponse>

    private val dataFactory = DataWrapperResponseFactory().create()

    @Before
    fun setUp() {
        pagingSource = CharactersPagingSource(remoteDataSource, "")
    }

    @Test
    fun `should return a success load result when load is called`() = runBlockingTest {

        whenever(remoteDataSource.fetchCharacters(any())).thenReturn(dataFactory)

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                null,
                2, false
            )
        )

        val expected = PagingSource.LoadResult.Page(
            listOf(
                CharacterFactory().create(CharacterFactory.Hero.IronMan),
                CharacterFactory().create(CharacterFactory.Hero.ABomb)
            ),
            null,
            20
        )

        Assert.assertEquals(expected, result)
    }

    @Test
    fun `should return an error result`() = runBlockingTest {

        val exception = RuntimeException()
        whenever(remoteDataSource.fetchCharacters(any())).thenThrow(exception)

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                null, 2, false
            )
        )

        Assert.assertEquals(PagingSource.LoadResult.Error<Int, Character>(exception), result)
    }
}
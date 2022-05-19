package com.pvsb.mockk.marvelapp.framework

import androidx.paging.PagingSource
import com.pvsb.core.data.repository.CharactersRemoteDataSource
import com.pvsb.factory.response.DataWrapperResponseFactory
import com.pvsb.marvelapp.data.network.model.DataWrapperResponse
import com.pvsb.marvelapp.data.paging.CharactersPagingSource
import com.pvsb.testmodule.CoroutineRule
import com.pvsb.testmodule.model.CharacterFactory
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PagingSourceTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    private lateinit var pagingSource: CharactersPagingSource

    private val remote: CharactersRemoteDataSource<DataWrapperResponse> = mockk()

    private val data = DataWrapperResponseFactory().create()

    @Before
    fun setUp() {
        pagingSource = CharactersPagingSource(remote, "")
    }

    @Test
    fun `should validate when the remote is called `() = runBlockingTest {
        coEvery { remote.fetchCharacters(any()) } returns (data)

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                0,
                20,
                false
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
}
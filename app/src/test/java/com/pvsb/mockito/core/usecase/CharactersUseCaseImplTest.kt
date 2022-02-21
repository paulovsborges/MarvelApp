package com.pvsb.mockito.core.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.pvsb.core.data.repository.CharactersRepository
import com.pvsb.core.usecase.CharactersUseCase
import com.pvsb.core.usecase.CharactersUseCaseImpl
import com.pvsb.testmodule.CoroutineRule
import com.pvsb.testmodule.model.CharacterFactory
import com.pvsb.testmodule.pagingsource.PagingSourceFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharactersUseCaseImplTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    private lateinit var charactersUseCase: CharactersUseCase

    @Mock
    private lateinit var repository: CharactersRepository

    private val charactersFactory = CharacterFactory().create(CharacterFactory.Hero.IronMan)

    private val pagingSource = PagingSourceFactory().create(listOf(charactersFactory))

    @Before
    fun setUp() {
        charactersUseCase = CharactersUseCaseImpl(repository)
    }

    @Test
    fun `should validate flow paging data creation when invoke from use case is called`() =
        runBlockingTest {
            whenever(repository.getCharacter("")).thenReturn(pagingSource)

            val result = charactersUseCase.invoke(
                CharactersUseCase.CharactersParams("", PagingConfig(20))
            )

            verify(repository).getCharacter("")

            Assert.assertNotNull(result)
        }
}
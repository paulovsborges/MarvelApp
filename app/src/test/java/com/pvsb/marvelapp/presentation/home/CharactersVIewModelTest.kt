package com.pvsb.marvelapp.presentation.home

import androidx.paging.PagingData
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.pvsb.core.domain.model.Character
import com.pvsb.core.usecase.CharactersUseCase
import com.pvsb.testmodule.CoroutineRule
import com.pvsb.testmodule.model.CharacterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
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
class CharactersVIewModelTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    @Mock
    lateinit var useCase: CharactersUseCase

    private lateinit var charactersViewModel: CharactersVIewModel

    private val charactersFactory = CharacterFactory()

    private val pagingDataCharacters = PagingData.from(
        listOf(
            charactersFactory.create(CharacterFactory.Hero.IronMan),
            charactersFactory.create(CharacterFactory.Hero.ABomb)
        )
    )

    @Before
    fun setUp() {
        charactersViewModel = CharactersVIewModel(useCase)
    }

    @Test
    fun `should validate the paging data object when calling the characters from remote`() =
        runBlockingTest {
            whenever(
                useCase.invoke(any())
            ).thenReturn(
                flowOf(
                    pagingDataCharacters
                )
            )

            val result = charactersViewModel.charactersPagingData("")

            Assert.assertNotNull(result)
        }

    @Test(expected = RuntimeException::class)
    fun `should throw an exception`() = runBlockingTest {

        whenever(useCase.invoke(any())).thenThrow(RuntimeException())

        charactersViewModel.charactersPagingData("")

    }
}
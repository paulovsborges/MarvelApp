package com.pvsb.mockk.marvelapp.presentation

import androidx.paging.PagingData
import com.pvsb.core.usecase.CharactersUseCase
import com.pvsb.marvelapp.presentation.home.CharactersVIewModel
import com.pvsb.testmodule.CoroutineRule
import com.pvsb.testmodule.model.CharacterFactory
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharactersViewModelTestMockk {

    @get:Rule
    val coroutineRule = CoroutineRule()

    private lateinit var viewModel: CharactersVIewModel

    private val useCase: CharactersUseCase = mockk()


    @Before
    fun setUp() {
        viewModel = CharactersVIewModel(useCase)
    }

    @Test
    fun `validate connection between view model and use case`() {

        every { useCase.invoke(any()) } returns (flow {
            PagingData.from(
                listOf(
                    CharacterFactory().create(CharacterFactory.Hero.IronMan),
                    CharacterFactory().create(CharacterFactory.Hero.ABomb)
                )
            )
        })

        val result = viewModel.charactersPagingData("")

        Assert.assertNotNull(result)
    }
}
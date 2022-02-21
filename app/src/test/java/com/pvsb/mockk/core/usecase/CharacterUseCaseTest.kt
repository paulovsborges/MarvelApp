package com.pvsb.mockk.core.usecase

import androidx.paging.PagingConfig
import com.pvsb.core.data.repository.CharactersRepository
import com.pvsb.core.usecase.CharactersUseCase
import com.pvsb.core.usecase.CharactersUseCaseImpl
import com.pvsb.testmodule.CoroutineRule
import com.pvsb.testmodule.model.CharacterFactory
import com.pvsb.testmodule.pagingsource.PagingSourceFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharacterUseCaseTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    private lateinit var useCase: CharactersUseCase

    private val repository: CharactersRepository = mockk()

    private val charactersFactory = CharacterFactory().create(CharacterFactory.Hero.IronMan)

    private val pagingSource = PagingSourceFactory().create(listOf(charactersFactory))

    @Before
    fun setUp() {
        useCase = CharactersUseCaseImpl(repository)
    }

    @Test
    fun `should validate connection between the use case and the repository`() {

        coEvery {
            repository.getCharacter("")
        } returns (pagingSource)

        val result = useCase.invoke(
            CharactersUseCase.CharactersParams("", PagingConfig(2))
        )

        coVerify { repository.getCharacter("") }

        Assert.assertNotNull(result)
    }
}
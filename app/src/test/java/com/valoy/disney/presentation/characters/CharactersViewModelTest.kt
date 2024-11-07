package com.valoy.disney.presentation.characters

import app.cash.turbine.turbineScope
import com.valoy.disney.CoroutineMainDispatcherRule
import com.valoy.disney.domain.action.GetCharacters
import com.valoy.disney.domain.model.Characters
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharactersViewModelTest {
    @get:Rule
    val coroutineRule = CoroutineMainDispatcherRule(StandardTestDispatcher())
    private val testCoroutineScope = TestScope(coroutineRule.dispatcher)
    private val getCharacters: GetCharacters = mockk()

    private lateinit var viewModel: CharactersViewModel

    @Before
    fun setUp() {
        viewModel = CharactersViewModel(getCharacters, coroutineRule.dispatcher)
    }

    @Test
    fun `getCharacters invoked should return characters`() =
        testCoroutineScope.runTest {
            coEvery { getCharacters() } returns characters

            viewModel.onGetCharacters()

            turbineScope {
                val state = viewModel.uiState.testIn(backgroundScope)
                val loading = state.awaitItem()
                val success = state.awaitItem()
                assertEquals(CharactersUiState.Loading, loading)
                assertEquals(CharactersUiState.Success(characters.toImmutableList()), success)
            }
        }

    companion object {
        val characters = listOf(
            Characters(
                enemies = emptyList(),
                films = emptyList(),
                imageUrl = "",
                name = "Character"
            )
        )
    }
}
package com.valoy.disney.presentation.characters

import com.valoy.disney.domain.model.Characters
import kotlinx.collections.immutable.ImmutableList

sealed class CharactersUiState {
    data object Loading : CharactersUiState()
    data class Success(val characters: ImmutableList<Characters>) : CharactersUiState()
    data class Error(val message: String) : CharactersUiState()
}
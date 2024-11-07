package com.valoy.disney.presentation.characters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valoy.disney.coroutines.safeRunCatching
import com.valoy.disney.domain.action.GetCharacters
import com.valoy.disney.domain.model.Characters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharacters: GetCharacters,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow<CharactersUiState>(CharactersUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun onGetCharacters() {
        viewModelScope.launch(dispatcher) {
            safeRunCatching {
                _uiState.update { CharactersUiState.Loading }
                _uiState.update { CharactersUiState.Success(getCharacters().toImmutableList()) }
            }.onFailure {
                Log.e("CharactersViewModel", "Error getting characters", it)
                _uiState.value = CharactersUiState.Error("Error getting characters")
            }
        }
    }
}
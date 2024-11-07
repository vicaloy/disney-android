package com.valoy.disney.presentation.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.valoy.disney.R
import com.valoy.disney.domain.model.Characters
import kotlinx.collections.immutable.ImmutableList

@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        viewModel.onGetCharacters()
    }

    when (uiState) {
        is CharactersUiState.Loading -> {
            Loading(modifier = modifier)
        }

        is CharactersUiState.Success -> {
            CharactersList(uiState.characters, modifier = modifier)
        }

        is CharactersUiState.Error -> {
            Alert(
                message = uiState.message,
                onRetry = viewModel::onGetCharacters,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun CharactersList(
    characters: ImmutableList<Characters>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(characters.size) {
            CharacterItem(characters[it])
        }
    }
}

@Composable
private fun CharacterItem(character: Characters, modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = character.name,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            model = character.imageUrl,
            placeholder = painterResource(R.drawable.placeholder),
            error = painterResource(R.drawable.placeholder_error),
            contentDescription = stringResource(R.string.placeholder),
        )
    }
}

@Composable
private fun Loading(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
private fun Alert(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    val showAlert = remember { mutableStateOf(true) }

    if (showAlert.value) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = { showAlert.value = false },
            confirmButton = {
                Button(onClick = { onRetry() }) {
                    Text(text = "Retry")
                }
            },
            dismissButton = {
                Button(onClick = { showAlert.value = false }) {
                    Text(text = "Dismiss")
                }
            },
            title = { Text(text = "Whoops") },
            text = { Text(text = message) })
    }
}
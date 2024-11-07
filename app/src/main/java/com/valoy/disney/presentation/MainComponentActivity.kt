package com.valoy.disney.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.valoy.disney.presentation.characters.CharactersScreen
import com.valoy.disney.presentation.theme.DisneyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainComponentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DisneyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CharactersScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
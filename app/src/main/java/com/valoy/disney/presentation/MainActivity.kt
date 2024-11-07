package com.valoy.disney.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.valoy.disney.databinding.ActivityMainBinding
import com.valoy.disney.presentation.characters.CharactersAdapter
import com.valoy.disney.presentation.characters.CharactersUiState
import com.valoy.disney.presentation.characters.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.onGetCharacters()

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { uiState ->
                    when (uiState) {
                        is CharactersUiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        is CharactersUiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            val characters = uiState.characters
                            binding.recyclerView.adapter =
                                CharactersAdapter(characters.toTypedArray())
                        }

                        is CharactersUiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }
}
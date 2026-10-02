package com.example.apppeliculas.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apppeliculas.model.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    var movieUiState: MovieUiState by mutableStateOf(MovieUiState.Loading)
        private set

    private val repository = MovieRepository()

    init {
        getMovies()
    }

    fun getMovies() {
        viewModelScope.launch {
            movieUiState = MovieUiState.Loading
            try {
                val movies = repository.getPopularMovies()
                movieUiState = MovieUiState.Success(movies)
            } catch (e: Exception) {
                movieUiState = MovieUiState.Error
            }
        }
    }
}
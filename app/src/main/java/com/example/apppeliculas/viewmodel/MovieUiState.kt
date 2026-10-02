package com.example.apppeliculas.viewmodel

import com.example.apppeliculas.model.Movie

sealed interface MovieUiState {
    data class Success(val movies: List<Movie>) : MovieUiState
    object Error : MovieUiState
    object Loading : MovieUiState
}
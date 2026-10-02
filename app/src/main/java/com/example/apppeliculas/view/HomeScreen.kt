package com.example.apppeliculas.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.apppeliculas.model.Movie
import com.example.apppeliculas.viewmodel.MovieUiState

@Composable
fun HomeScreen(
    movieUiState: MovieUiState,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    when (movieUiState) {
        is MovieUiState.Loading -> LoadingScreen(modifier)
        is MovieUiState.Success -> MoviesGridScreen(movieUiState.movies, contentPadding, modifier)
        is MovieUiState.Error -> ErrorScreen(modifier)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Error al cargar las películas. Revisa tu conexión a Internet.")
    }
}

@Composable
fun MoviesGridScreen(
    movies: List<Movie>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    // Estado para guardar la película seleccionada
    var selectedMovie by remember { mutableStateOf<Movie?>(null) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies) { movie ->
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w342${movie.posterPath}",
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f)
                    .clickable { selectedMovie = movie } // <-- Hace que la imagen sea clickeable
            )
        }
    }

    // Ventana emergente (Dialog) que aparece al hacer clic
    if (selectedMovie != null) {
        AlertDialog(
            onDismissRequest = { selectedMovie = null },
            title = { Text(text = selectedMovie!!.title) },
            text = { Text(text = "Puntuación: ${selectedMovie!!.voteAverage} / 10") },
            confirmButton = {
                TextButton(onClick = { selectedMovie = null }) {
                    Text("Cerrar")
                }
            }
        )
    }
}
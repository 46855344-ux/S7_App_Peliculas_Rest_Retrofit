package com.example.apppeliculas.model

class MovieRepository {
    suspend fun getPopularMovies(): List<Movie> {
        return MovieApiClient.service.getPopularMovies().results
    }
}
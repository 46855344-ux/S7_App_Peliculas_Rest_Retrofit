package com.example.apppeliculas.model

import com.google.gson.annotations.SerializedName

data class MoviesListResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<Movie>
)
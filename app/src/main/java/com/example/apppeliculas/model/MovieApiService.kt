package com.example.apppeliculas.model

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = "fd46a4d6cd6d995f17169596a7231eca" // Tu API KEY

private val requestInterceptor = Interceptor { chain ->
    val url = chain.request().url.newBuilder()
        .addQueryParameter("api_key", API_KEY)
        .build()
    val request = chain.request().newBuilder().url(url).build()
    chain.proceed(request)
}

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(requestInterceptor)
    .connectTimeout(60, TimeUnit.SECONDS)
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int = 1): MoviesListResponse
}

object MovieApiClient {
    val service: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}
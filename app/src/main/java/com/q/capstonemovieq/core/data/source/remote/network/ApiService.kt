package com.q.capstonemovieq.core.data.source.remote.network

import com.q.capstonemovieq.BuildConfig
import com.q.capstonemovieq.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getMovie(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): ListMovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("api_key") apiKey: String): ListMovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): ListMovieResponse
}
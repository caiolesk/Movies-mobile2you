package com.example.data.remote.api

import com.example.data.remote.model.GenresListPayload
import com.example.data.remote.model.MoviePayload
import com.example.data.remote.model.MoviesPayload
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    companion object {
        private const val API_KEY = "468131cccdfc0754786fda8ed67a3fae"
    }

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): MoviePayload

    @GET("movie/{movie_id}/similar")
    suspend fun fetchMoviesSimilar(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int
    ): MoviesPayload

    @GET("genre/movie/list")
    suspend fun fetchGenresList(@Query("api_key") apiKey: String = API_KEY): GenresListPayload
}
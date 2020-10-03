package com.example.data.remote.api

import com.example.data.remote.model.MoviePayload
import com.example.data.remote.model.MoviesPayload
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/{movie_id}")
    fun fetchMovieDetails(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String): Observable<MoviePayload>

    @GET("movie/{movie_id}/similar")
    fun fetchMoviesSimilar(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String ): Observable<MoviesPayload>
}
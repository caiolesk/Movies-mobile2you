package com.example.data.remote.source

import com.example.domain.entities.Movie
import io.reactivex.Observable

interface MovieDataSource {

    fun fetchMovieDetails(apiKey: String, movieId: Int): Observable<Movie>

    fun fetchMoviesSimilar(apiKey: String, movieId: Int): Observable<List<Movie>>
}
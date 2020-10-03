package com.example.domain.repository

import com.example.domain.entities.Genres
import com.example.domain.entities.Movie
import io.reactivex.Observable

interface MovieRepository {

    fun fetchMovieDetails(apiKey: String, movieId: Int): Observable<Movie>

    fun fetchMoviesSimilar(apiKey: String, movieId: Int): Observable<List<Movie>>

    fun fetchGenresList(apiKey: String): Observable<List<Genres>>
}
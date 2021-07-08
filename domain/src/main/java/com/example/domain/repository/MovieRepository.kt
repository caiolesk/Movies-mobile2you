package com.example.domain.repository

import com.example.domain.entities.Genres
import com.example.domain.entities.Movie
import io.reactivex.Observable

interface MovieRepository {

    fun fetchMovieDetails(movieId: Int): Observable<Movie>

    fun fetchMoviesSimilar(
        movieId: Int,
        page: Int
    ): Observable<List<Movie>>

    fun fetchGenresList(): Observable<List<Genres>>
}
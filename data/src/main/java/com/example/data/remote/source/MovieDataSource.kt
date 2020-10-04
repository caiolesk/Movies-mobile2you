package com.example.data.remote.source

import com.example.domain.entities.Genres
import com.example.domain.entities.Movie
import io.reactivex.Observable

interface MovieDataSource {

    fun fetchMovieDetails(movieId: Int): Observable<Movie>

    fun fetchMoviesSimilar(movieId: Int): Observable<List<Movie>>

    fun fetchGenresList(): Observable<List<Genres>>
}
package com.example.domain.repository

import com.example.domain.entities.Genres
import com.example.domain.entities.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun fetchMovieDetails(movieId: Int): Flow<Movie>

    fun fetchMoviesSimilar(
        movieId: Int,
        page: Int
    ): Flow<List<Movie>>

    fun fetchGenresList(): Flow<List<Genres>>
}
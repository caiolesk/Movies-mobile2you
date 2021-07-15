package com.example.domain.usecases

import com.example.domain.entities.Genres
import com.example.domain.entities.Movie
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    fun fetchMovieDetails(movieId: Int): Flow<Movie> {
        return repository.fetchMovieDetails(movieId)
    }

    fun fetchMoviesSimilar(
        movieId: Int,
        page: Int
    ): Flow<List<Movie>> {
        return repository.fetchMoviesSimilar(
            movieId = movieId,
            page = page
        )
    }

    fun fetchGenresList(): Flow<List<Genres>> {
        return repository.fetchGenresList()
    }
}
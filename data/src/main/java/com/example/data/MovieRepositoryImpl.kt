package com.example.data

import com.example.data.remote.source.MovieDataSource
import com.example.domain.entities.Genres
import com.example.domain.entities.Movie
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieDataSource
) : MovieRepository {

    override fun fetchMovieDetails(movieId: Int): Flow<Movie> {
        return remoteDataSource.fetchMovieDetails(movieId)
    }

    override fun fetchMoviesSimilar(
        movieId: Int,
        page: Int
    ): Flow<List<Movie>> {
        return remoteDataSource.fetchMoviesSimilar(
            movieId = movieId,
            page = page
        )
    }

    override fun fetchGenresList(): Flow<List<Genres>> {
        return remoteDataSource.fetchGenresList()
    }
}
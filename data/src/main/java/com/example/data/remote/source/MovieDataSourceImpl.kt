package com.example.data.remote.source

import com.example.data.remote.api.MovieApi
import com.example.data.remote.mapper.GenresMapper
import com.example.data.remote.mapper.MovieMapper
import com.example.domain.entities.Genres
import com.example.domain.entities.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieDataSource {

    override fun fetchMovieDetails(movieId: Int): Flow<Movie> {
        return flow {
            emit(
                MovieMapper.map(
                    movieApi.fetchMovieDetails(movieId)
                )
            )
        }
    }

    override fun fetchMoviesSimilar(
        movieId: Int,
        page: Int
    ): Flow<List<Movie>> {
        return flow {
            emit(
                MovieMapper.map(
                    movieApi.fetchMoviesSimilar(
                        movieId = movieId,
                        page = page
                    )
                )
            )
        }
    }

    override fun fetchGenresList(): Flow<List<Genres>> {
        return flow {
            emit(
                GenresMapper.map(
                    movieApi.fetchGenresList()
                )
            )
        }
    }
}
package com.example.data.remote.source

import com.example.data.remote.api.MovieApi
import com.example.data.remote.mapper.GenresMapper
import com.example.data.remote.mapper.MovieMapper
import com.example.domain.entities.Genres
import com.example.domain.entities.Movie
import io.reactivex.Observable
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieDataSource {

    override fun fetchMovieDetails(movieId: Int): Observable<Movie> {
        return movieApi.fetchMovieDetails(movieId)
            .map { MovieMapper.map(it) }
    }

    override fun fetchMoviesSimilar(
        movieId: Int,
        page: Int
    ): Observable<List<Movie>> {
        return movieApi.fetchMoviesSimilar(
            movieId = movieId,
            page = page
        ).map { MovieMapper.map(it) }
    }

    override fun fetchGenresList(): Observable<List<Genres>> {
        return movieApi.fetchGenresList()
            .map { GenresMapper.map(it) }
    }
}
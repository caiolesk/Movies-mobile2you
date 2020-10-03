package com.example.data.remote.source

import com.example.data.remote.api.MovieApi
import com.example.data.remote.mapper.MovieMapper
import com.example.domain.entities.Movie
import io.reactivex.Observable

class MovieDataSourceImpl (
    private val movieApi: MovieApi
) : MovieDataSource {

    override fun fetchMovieDetails(apiKey: String, movieId: Int): Observable<Movie> {
        return movieApi.fetchMovieDetails(movieId, apiKey)
            .map { MovieMapper.map(it) }
    }

    override fun fetchMoviesSimilar(apiKey: String, movieId: Int): Observable<List<Movie>> {
        return movieApi.fetchMoviesSimilar(movieId, apiKey)
            .map { MovieMapper.map(it) }
    }

}
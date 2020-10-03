package com.example.data

import com.example.data.remote.source.MovieDataSource
import com.example.domain.entities.Movie
import com.example.domain.repository.MovieRepository
import io.reactivex.Observable

class MovieRepositoryImpl (
    private val remoteDataSource: MovieDataSource
) : MovieRepository {

    override fun fetchMovieDetails(apiKey: String, movieId: Int): Observable<Movie> {
        return remoteDataSource.fetchMovieDetails(apiKey, movieId)
            .flatMap { listFav ->
                Observable.just(listFav)
            }
    }

    override fun fetchMoviesSimilar(apiKey: String, movieId: Int): Observable<List<Movie>> {
        return remoteDataSource.fetchMoviesSimilar(apiKey, movieId)
            .flatMap { listFav ->
                Observable.just(listFav)
            }
    }

}
package com.example.data

import com.example.data.remote.source.MovieDataSource
import com.example.domain.entities.Genres
import com.example.domain.entities.Movie
import com.example.domain.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieDataSource
) : MovieRepository {

    override fun fetchMovieDetails(movieId: Int): Observable<Movie> {
        return remoteDataSource.fetchMovieDetails(movieId)
            .flatMap { listFav ->
                Observable.just(listFav)
            }
    }

    override fun fetchMoviesSimilar(
        movieId: Int,
        page: Int
    ): Observable<List<Movie>> {
        return remoteDataSource.fetchMoviesSimilar(
            movieId = movieId,
            page = page
        ).flatMap { listFav ->
            Observable.just(listFav)
        }
    }

    override fun fetchGenresList(): Observable<List<Genres>> {
        return remoteDataSource.fetchGenresList()
            .flatMap { listFav ->
                Observable.just(listFav)
            }
    }

}
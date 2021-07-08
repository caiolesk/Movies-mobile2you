package com.example.domain.usecases

import com.example.domain.di.IOScheduler
import com.example.domain.entities.Genres
import com.example.domain.entities.Movie
import com.example.domain.repository.MovieRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepository,
    @IOScheduler private val scheduler: Scheduler
) {
    fun fetchMovieDetails(movieId: Int): Observable<Movie> {
        return repository.fetchMovieDetails(movieId)
            .subscribeOn(scheduler)
    }

    fun fetchMoviesSimilar(
        movieId: Int,
        page: Int
    ): Observable<List<Movie>> {
        return repository.fetchMoviesSimilar(
            movieId = movieId,
            page = page
        ).subscribeOn(scheduler)
    }

    fun fetchGenresList(): Observable<List<Genres>> {
        return repository.fetchGenresList()
            .subscribeOn(scheduler)
    }
}
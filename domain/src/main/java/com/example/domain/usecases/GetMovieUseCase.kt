package com.example.domain.usecases

import com.example.domain.entities.Genres
import com.example.domain.entities.Movie
import com.example.domain.repository.MovieRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetMovieUseCase (
    private val repository: MovieRepository,
    private val scheduler: Scheduler
){
    fun fetchMovieDetails(movieId: Int): Observable<Movie>{
        return repository.fetchMovieDetails(movieId)
            .subscribeOn(scheduler)
    }

    fun fetchMoviesSimilar(movieId: Int): Observable<List<Movie>>{
        return repository.fetchMoviesSimilar(movieId)
            .subscribeOn(scheduler)
    }

    fun fetchGenresList(): Observable<List<Genres>>{
        return repository.fetchGenresList()
            .subscribeOn(scheduler)
    }
}
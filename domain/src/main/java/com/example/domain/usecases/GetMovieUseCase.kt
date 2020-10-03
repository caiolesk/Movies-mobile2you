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
    fun fetchMovieDetails(apiKey: String, movieId: Int): Observable<Movie>{
        return repository.fetchMovieDetails(apiKey,movieId)
            .subscribeOn(scheduler)
    }

    fun fetchMoviesSimilar(apiKey: String, movieId: Int): Observable<List<Movie>>{
        return repository.fetchMoviesSimilar(apiKey,movieId)
            .subscribeOn(scheduler)
    }

    fun fetchGenresList(apiKey: String): Observable<List<Genres>>{
        return repository.fetchGenresList(apiKey)
            .subscribeOn(scheduler)
    }
}
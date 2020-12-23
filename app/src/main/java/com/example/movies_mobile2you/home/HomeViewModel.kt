package com.example.movies_mobile2you.home

import androidx.hilt.lifecycle.ViewModelInject
import com.example.domain.di.UIScheduler
import com.example.domain.entities.Genres
import com.example.domain.entities.Movie
import com.example.domain.usecases.GetMovieUseCase
import com.example.movies_mobile2you.viewmodel.BaseViewModel
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.plusAssign

class HomeViewModel @ViewModelInject constructor(
    private val useCase: GetMovieUseCase,
    @UIScheduler val uiScheduler: Scheduler,
    private val router: HomeRouter
) : BaseViewModel<HomeIntent, HomeState>() {

    lateinit var genres: MutableList<Genres>

    override fun handle(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.StartData -> fetchGenresList()
            is HomeIntent.OpenDetail -> handleRouteDetail(intent.movie)
        }
    }

    private fun fetchMovieDetails(movieId: Int) {
        compositeDisposable += useCase.fetchMovieDetails(movieId)
            .observeOn(uiScheduler)
            .subscribe(
                {
                    _state.value = HomeState.SuccessDetail(it)
                },
                {
                    _state.value = HomeState.Error
                }
            )
    }

    private fun fetchMoviesSimilar(movieId: Int) {
        compositeDisposable += useCase.fetchMoviesSimilar(movieId)
            .observeOn(uiScheduler)
            .subscribe(
                {
                    _state.value = HomeState.SuccessSimilarMovies(it, genres)
                },
                {
                    _state.value = HomeState.Error
                }
            )
    }

    private fun fetchGenresList() {
        _state.value = HomeState.Loading(true)
        compositeDisposable += useCase.fetchGenresList()
            .observeOn(uiScheduler)
            .subscribe(
                {
                    genres = it.toMutableList()
                    fetchMovieDetails(movieId)
                    fetchMoviesSimilar(movieId)
                },
                {
                    _state.value = HomeState.Error
                }
            )
    }

    private fun handleRouteDetail(movie: Movie) {
        router.routeToDetail(movie)
    }

    companion object {
        private const val movieId = 497582
    }
}
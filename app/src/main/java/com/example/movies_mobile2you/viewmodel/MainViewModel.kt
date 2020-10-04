package com.example.movies_mobile2you.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.domain.entities.Genres
import com.example.domain.entities.Movie
import com.example.domain.usecases.GetMovieUseCase
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.plusAssign

class MainViewModel(
    val useCase: GetMovieUseCase,
    val uiScheduler: Scheduler
) : BaseViewModel() {

    val stateMovieDetails = MutableLiveData<ViewState<Movie>>().apply {
        value = ViewState.Loading
    }

    val stateMoviesSimilar = MutableLiveData<ViewState<List<Movie>>>().apply {
        value = ViewState.Loading
    }

    val stateGenres = MutableLiveData<ViewState<List<Genres>>>().apply {
        value = ViewState.Loading
    }

    fun fetchMovieDetails(movieId: Int){
        disposable += useCase.fetchMovieDetails(movieId)
            .compose(StateMachineObserver())
            .observeOn(uiScheduler)
            .subscribe(
                {
                    stateMovieDetails.postValue(it)
                },
                {

                }
            )
    }

    fun fetchMoviesSimilar(movieId: Int){
        disposable += useCase.fetchMoviesSimilar(movieId)
            .compose(StateMachineObserver())
            .observeOn(uiScheduler)
            .subscribe(
                {
                    stateMoviesSimilar.postValue(it)
                },
                {

                }
            )
    }

    fun fetchGenresList(){
        disposable += useCase.fetchGenresList()
            .compose(StateMachineObserver())
            .observeOn(uiScheduler)
            .subscribe(
                {
                    stateGenres.postValue(it)
                },
                {

                }
            )
    }

}
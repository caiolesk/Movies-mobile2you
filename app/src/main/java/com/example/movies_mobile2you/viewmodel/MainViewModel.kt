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

    val retryData = MutableLiveData<Boolean>()

    val state = MutableLiveData<ViewState<Movie>>().apply {
        value = ViewState.Loading
    }

    val stateMoviesSimilar = MutableLiveData<ViewState<List<Movie>>>().apply {
        value = ViewState.Loading
    }

    val stateGenres = MutableLiveData<ViewState<List<Genres>>>().apply {
        value = ViewState.Loading
    }

    fun fetchMovieDetails(apiKey: String, movieId: Int){
        disposable += useCase.fetchMovieDetails(apiKey, movieId)
            .compose(StateMachineObserver())
            .observeOn(uiScheduler)
            .subscribe(
                {
                    state.postValue(it)
                },
                {

                }
            )
    }

    fun fetchMoviesSimilar(apiKey: String, movieId: Int){
        disposable += useCase.fetchMoviesSimilar(apiKey, movieId)
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

    fun fetchGenresList(apiKey: String){
        disposable += useCase.fetchGenresList(apiKey)
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

    fun onRetryData(){
        retryData.postValue(true)
    }
}
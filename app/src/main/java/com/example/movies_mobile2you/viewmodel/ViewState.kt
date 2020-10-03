package com.example.movies_mobile2you.viewmodel

import io.reactivex.*

sealed class ViewState<out T> {
    object Loading: ViewState<Nothing>()
    data class Sucess<T>(val data: T): ViewState<T>()
    data class Failed<T>(val throwable: Throwable): ViewState<T>()
}

class StateMachineObserver<T>: ObservableTransformer<T, ViewState<T>> {
    override fun apply(upstream: Observable<T>): ObservableSource<ViewState<T>> {
        return upstream
            .map {
                ViewState.Sucess(it) as ViewState<T>
            }
            .onErrorReturn {
                ViewState.Failed(it)
            }
            .doOnSubscribe {
                ViewState.Loading
            }
    }
}
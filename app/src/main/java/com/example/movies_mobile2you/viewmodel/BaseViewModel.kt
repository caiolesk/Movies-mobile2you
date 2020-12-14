package com.example.movies_mobile2you.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<Intent, State> : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    protected val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    abstract fun handle(intent: Intent)

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

package com.example.movies_mobile2you.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel: ViewModel() {

    val disposable = CompositeDisposable()

    override fun onCleared() {
        disposable.clear()

        super.onCleared()
    }
}
package com.example.movies_mobile2you.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<Intent, State> : ViewModel() {

    protected val _state = MutableStateFlow<State?>(null)
    val state: StateFlow<State?> = _state

    abstract fun handle(intent: Intent)
}

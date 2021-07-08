package com.example.movies_mobile2you.adapter

sealed class LoadState {
    object Loading: LoadState()
    object Done: LoadState()
}
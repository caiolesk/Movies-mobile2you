package com.example.movies_mobile2you.home

import com.example.domain.entities.Movie

sealed class HomeIntent {
    object StartData : HomeIntent()
    data class OpenDetail(val movie: Movie) : HomeIntent()
    object NewPage : HomeIntent()
}

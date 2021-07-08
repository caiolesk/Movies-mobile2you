package com.example.movies_mobile2you.home

import com.example.domain.entities.Genres
import com.example.domain.entities.Movie

sealed class HomeState {
    data class SuccessDetail(val movie: Movie) : HomeState()
    data class SuccessSimilarMovies(val similarMovies: List<Movie>, val genres: List<Genres>) : HomeState()
    object Error : HomeState()
}
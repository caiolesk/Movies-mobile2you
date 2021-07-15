package com.example.movies_mobile2you.home

import androidx.lifecycle.viewModelScope
import com.example.domain.entities.Genres
import com.example.domain.entities.Movie
import com.example.domain.usecases.GetMovieUseCase
import com.example.movies_mobile2you.extension.onCollect
import com.example.movies_mobile2you.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetMovieUseCase,
    private val router: HomeRouter
) : BaseViewModel<HomeIntent, HomeState>() {

    private var allItems = listOf<Movie>()
    private var shouldLoadNewItems = true
    private var page = FIRST_PAGE
    private lateinit var genres: MutableList<Genres>

    override fun handle(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.StartData -> fetchGenresList()
            is HomeIntent.OpenDetail -> handleRouteDetail(intent.movie)
            HomeIntent.NewPage -> requestNewPage()
        }
    }

    private fun requestNewPage() {
        fetchMoviesSimilar(page + 1)
    }

    private fun fetchMovieDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.fetchMovieDetails(movieId).onCollect(
                onSuccess = {
                    _state.value = HomeState.SuccessDetail(it)
                },
                onError = {
                    _state.value = HomeState.Error
                }
            )
        }
    }

    private fun fetchMoviesSimilar(
        page: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.fetchMoviesSimilar(
                movieId = movieId,
                page = page
            ).onCollect(
                onSuccess = {
                    setPage(page)
                    updateItems(
                        page = page,
                        items = it
                    )
                },
                onError = {
                    _state.value = HomeState.Error
                }
            )
        }
    }

    private fun fetchGenresList() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.fetchGenresList()
                .onCollect(
                    onSuccess = {
                        genres = it.toMutableList()
                        fetchMovieDetails()
                        fetchMoviesSimilar(FIRST_PAGE)
                    },
                    onError = {
                        _state.value = HomeState.Error
                    }
                )
        }
    }

    private fun handleRouteDetail(movie: Movie) {
        router.routeToDetail(movie)
    }

    private fun setPage(page: Int) {
        this.page = page
    }

    private fun updateItems(
        page: Int,
        items: List<Movie>
    ) {
        shouldLoadNewItems = items.isNotEmpty()
        allItems = if (page == 0) {
            items
        } else {
            allItems + items
        }
        _state.value = HomeState.SuccessSimilarMovies(
            similarMovies = allItems,
            genres = genres
        )
    }

    companion object {
        private const val movieId = 497582
        private const val FIRST_PAGE = 1
    }
}
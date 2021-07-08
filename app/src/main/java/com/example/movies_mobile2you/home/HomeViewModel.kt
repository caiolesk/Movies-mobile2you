package com.example.movies_mobile2you.home

import com.example.domain.di.UIScheduler
import com.example.domain.entities.Genres
import com.example.domain.entities.Movie
import com.example.domain.usecases.GetMovieUseCase
import com.example.movies_mobile2you.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetMovieUseCase,
    @UIScheduler val uiScheduler: Scheduler,
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
        compositeDisposable += useCase.fetchMovieDetails(movieId)
            .observeOn(uiScheduler)
            .subscribe(
                {
                    _state.value = HomeState.SuccessDetail(it)
                },
                {
                    _state.value = HomeState.Error
                }
            )
    }

    private fun fetchMoviesSimilar(
        page: Int
    ) {
        compositeDisposable += useCase.fetchMoviesSimilar(
            movieId = movieId,
            page = page
        ).observeOn(uiScheduler)
            .subscribe(
                {
                    setPage(page)
                    updateItems(
                        page = page,
                        items = it
                    )
                },
                {
                    _state.value = HomeState.Error
                }
            )
    }

    private fun fetchGenresList() {
        compositeDisposable += useCase.fetchGenresList()
            .observeOn(uiScheduler)
            .subscribe(
                {
                    genres = it.toMutableList()
                    fetchMovieDetails()
                    fetchMoviesSimilar(FIRST_PAGE)
                },
                {
                    _state.value = HomeState.Error
                }
            )
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
package com.example.movies_mobile2you.di

import com.example.movies_mobile2you.adapter.MovieAdapter
import com.example.movies_mobile2you.viewmodel.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    factory { MovieAdapter() }

    viewModel {
        MainViewModel(
            useCase = get(),
            uiScheduler = AndroidSchedulers.mainThread()
        )
    }
}
package com.example.data.di

import com.example.data.MovieRepositoryImpl
import com.example.domain.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<MovieRepository> {
        MovieRepositoryImpl(
            remoteDataSource = get()
        )
    }
}

val dataModules = listOf(remoteDataSourceModule,repositoryModule)
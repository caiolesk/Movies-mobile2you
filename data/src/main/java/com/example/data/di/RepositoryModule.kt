package com.example.data.di

import com.example.data.MovieRepositoryImpl
import com.example.data.remote.source.MovieDataSource
import com.example.data.remote.source.MovieDataSourceImpl
import com.example.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun providesMovieRepository(repo: MovieRepositoryImpl): MovieRepository

    @Singleton
    @Binds
    abstract fun providesMovieDataSource(repo: MovieDataSourceImpl): MovieDataSource
}
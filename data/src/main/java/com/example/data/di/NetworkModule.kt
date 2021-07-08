package com.example.data.di

import com.example.data.remote.api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesMovieApi(retrofit: Retrofit.Builder): MovieApi {
        return retrofit
            .build()
            .create(MovieApi::class.java)
    }
}
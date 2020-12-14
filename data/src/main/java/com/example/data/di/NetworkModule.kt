package com.example.data.di

import com.example.data.remote.api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesMovieApi(retrofit: Retrofit.Builder): MovieApi {
        return retrofit
            .build()
            .create(MovieApi::class.java)
    }
}
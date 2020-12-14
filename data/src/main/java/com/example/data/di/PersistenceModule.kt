package com.example.data.di

import com.example.domain.di.IOScheduler
import com.example.domain.di.UIScheduler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
@InstallIn(ApplicationComponent::class)
object PersistenceModule {

    @Provides
    @IOScheduler
    fun providesIOScheduler(): Scheduler {
        return Schedulers.io()
    }

    @Provides
    @UIScheduler
    fun providesUIScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}
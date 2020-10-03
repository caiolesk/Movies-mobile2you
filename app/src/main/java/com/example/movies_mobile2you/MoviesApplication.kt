package com.example.movies_mobile2you

import android.app.Application
import com.example.data.di.dataModules
import com.example.domain.di.domainModule
import com.example.movies_mobile2you.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoviesApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MoviesApplication)

            modules(domainModule + dataModules + listOf(presentationModule))
        }
    }
}
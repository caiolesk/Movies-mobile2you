package com.example.domain.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IOScheduler

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UIScheduler
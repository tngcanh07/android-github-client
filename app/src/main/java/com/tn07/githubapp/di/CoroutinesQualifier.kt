package com.tn07.githubapp.di

import javax.inject.Qualifier

/**
 * Created by toannguyen
 * Jun 28, 2021 at 00:06
 */

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher

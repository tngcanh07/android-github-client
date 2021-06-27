package com.tn07.githubapp.di

import com.tn07.githubapp.BuildConfig
import com.tn07.githubapp.data.remote.api.ApiConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by toannguyen
 * Jun 27, 2021 at 23:36
 */
@Module
@InstallIn(SingletonComponent::class)
class AppConfigModule {
    @Provides
    @Singleton
    fun providesApiConfig(): ApiConfig {
        return ApiConfig(
            baseUrl = "https://api.github.com/",
            userAgent = BuildConfig.APPLICATION_ID,
            acceptHeader = "application/vnd.github.v3+json"
        )
    }
}
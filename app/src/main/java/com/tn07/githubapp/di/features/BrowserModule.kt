package com.tn07.githubapp.di.features

import com.tn07.githubapp.domain.SearchUsersUseCase
import com.tn07.githubapp.domain.SearchUsersUseCaseImpl
import com.tn07.githubapp.presentation.browser.transformer.GitUserBrowserTransformer
import com.tn07.githubapp.presentation.browser.transformer.GitUserBrowserTransformerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent

/**
 * Created by toannguyen
 * Jun 26, 2021 at 11:05
 */
@Module
@InstallIn(SingletonComponent::class)
interface BrowserModule {
    @Binds
    fun bindTransformer(impl: GitUserBrowserTransformerImpl): GitUserBrowserTransformer

    @Binds
    fun bindSearchUsersUseCase(impl: SearchUsersUseCaseImpl): SearchUsersUseCase
}
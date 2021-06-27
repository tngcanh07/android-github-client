package com.tn07.githubapp.data.di

import com.tn07.githubapp.data.GitUserRepositoryImpl
import com.tn07.githubapp.data.remote.GitUserRemoteDataSource
import com.tn07.githubapp.data.remote.GitUserRemoteDataSourceImpl
import com.tn07.githubapp.domain.GitUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by toannguyen
 * Jun 25, 2021 at 21:32
 */
@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindGitUserRepository(impl: GitUserRepositoryImpl): GitUserRepository

    @Binds
    fun bindGitUserRemoteDataSource(impl: GitUserRemoteDataSourceImpl): GitUserRemoteDataSource
}
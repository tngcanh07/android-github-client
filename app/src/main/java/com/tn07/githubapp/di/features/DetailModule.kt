package com.tn07.githubapp.di.features

import com.tn07.githubapp.domain.GetUserDetailUseCase
import com.tn07.githubapp.domain.GetUserDetailUseCaseImpl
import com.tn07.githubapp.presentation.detail.transformer.GitUserDetailTransformer
import com.tn07.githubapp.presentation.detail.transformer.GitUserDetailTransformerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by toannguyen
 * Jun 27, 2021 at 10:29
 */
@Module
@InstallIn(SingletonComponent::class)
interface DetailModule {
    @Binds
    fun bindGetUserDetailUseCase(impl: GetUserDetailUseCaseImpl): GetUserDetailUseCase

    @Binds
    fun bindTransformer(impl: GitUserDetailTransformerImpl): GitUserDetailTransformer
}

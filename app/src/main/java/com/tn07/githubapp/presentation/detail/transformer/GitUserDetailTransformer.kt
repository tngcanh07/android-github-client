package com.tn07.githubapp.presentation.detail.transformer

import com.tn07.githubapp.domain.entities.GitUserDetail
import com.tn07.githubapp.domain.exceptions.DomainException
import com.tn07.githubapp.presentation.detail.uimodel.LoadDetailError
import com.tn07.githubapp.presentation.detail.uimodel.UserDetailUiModel

/**
 * Created by toannguyen
 * Jun 27, 2021 at 10:15
 */
interface GitUserDetailTransformer {
    fun transformUserDetail(gitUserDetail: GitUserDetail): UserDetailUiModel

    fun transformErrorState(domainException: DomainException): LoadDetailError
}
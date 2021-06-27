package com.tn07.githubapp.presentation.browser.transformer

import com.tn07.githubapp.domain.entities.GitUser
import com.tn07.githubapp.domain.exceptions.DomainException
import com.tn07.githubapp.presentation.browser.uimodel.GitUserUiModel
import com.tn07.githubapp.presentation.browser.uimodel.PageState

/**
 * Created by toannguyen
 * Jun 26, 2021 at 11:03
 */
interface GitUserBrowserTransformer {
    fun transformGitUserUiModel(user: GitUser): GitUserUiModel

    fun transformErrorState(domainException: DomainException): PageState.Error

    fun transformLoadingNextError(domainException: DomainException): PageState.LoadingNextError
}
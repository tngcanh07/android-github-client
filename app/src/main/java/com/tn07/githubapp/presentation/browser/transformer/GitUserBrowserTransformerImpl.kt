package com.tn07.githubapp.presentation.browser.transformer

import com.tn07.githubapp.R
import com.tn07.githubapp.domain.entities.GitUser
import com.tn07.githubapp.domain.exceptions.ConnectionException
import com.tn07.githubapp.domain.exceptions.DomainException
import com.tn07.githubapp.domain.exceptions.RateLimitException
import com.tn07.githubapp.presentation.browser.uimodel.GitUserUiModel
import com.tn07.githubapp.presentation.browser.uimodel.PageState
import javax.inject.Inject

/**
 * Created by toannguyen
 * Jun 26, 2021 at 11:03
 */
class GitUserBrowserTransformerImpl @Inject constructor() : GitUserBrowserTransformer {
    override fun transformGitUserUiModel(user: GitUser): GitUserUiModel {
        return GitUserUiModel(
            username = user.login,
            avatar = user.avatarUrl
        )
    }

    override fun transformErrorState(domainException: DomainException): PageState.Error {
        return PageState.Error(getErrorMessage(domainException))
    }

    override fun transformLoadingNextError(domainException: DomainException): PageState.LoadingNextError {
        return PageState.LoadingNextError(getErrorMessage(domainException))
    }

    private fun getErrorMessage(domainException: DomainException): Int {
        return when (domainException) {
            is ConnectionException -> R.string.request_error_connection_message
            is RateLimitException -> R.string.request_error_rate_limit_message
            else -> R.string.request_error_unknown_message
        }
    }
}
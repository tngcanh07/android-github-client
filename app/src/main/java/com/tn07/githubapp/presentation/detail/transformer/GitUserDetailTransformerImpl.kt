package com.tn07.githubapp.presentation.detail.transformer

import androidx.annotation.StringRes
import com.tn07.githubapp.R
import com.tn07.githubapp.domain.entities.GitUserDetail
import com.tn07.githubapp.domain.exceptions.ConnectionException
import com.tn07.githubapp.domain.exceptions.DomainException
import com.tn07.githubapp.domain.exceptions.RateLimitException
import com.tn07.githubapp.presentation.detail.uimodel.LoadDetailError
import com.tn07.githubapp.presentation.detail.uimodel.UserDetailUiModel
import java.text.DecimalFormat
import javax.inject.Inject

/**
 * Created by toannguyen
 * Jun 27, 2021 at 10:15
 */
class GitUserDetailTransformerImpl @Inject constructor() : GitUserDetailTransformer {
    private val decimalFormat: DecimalFormat by lazy {
        DecimalFormat("###,###,###")
    }

    override fun transformUserDetail(gitUserDetail: GitUserDetail): UserDetailUiModel {
        return UserDetailUiModel(
            username = gitUserDetail.login,
            name = gitUserDetail.name,
            avatar = gitUserDetail.avatarUrl,
            publicRepos = decimalFormat.format(gitUserDetail.publicRepos),
            followers = decimalFormat.format(gitUserDetail.followers),
            following = decimalFormat.format(gitUserDetail.following),
            company = gitUserDetail.company,
            blog = gitUserDetail.blog,
            email = gitUserDetail.email,
            location = gitUserDetail.location,
            htmlUrl = gitUserDetail.htmlUrl
        )
    }

    override fun transformErrorState(domainException: DomainException): LoadDetailError {
        @StringRes val errorMessageStringRes = when (domainException) {
            is ConnectionException -> R.string.request_error_connection_message
            is RateLimitException -> R.string.request_error_rate_limit_message
            else -> R.string.request_error_unknown_message
        }
        return LoadDetailError(errorMessageStringRes)
    }
}
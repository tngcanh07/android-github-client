package com.tn07.githubapp.presentation.browser.transformer

import com.tn07.githubapp.domain.entities.GitUser
import com.tn07.githubapp.presentation.browser.uimodel.GitUserUiModel
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
}
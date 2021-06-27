package com.tn07.githubapp.presentation.browser.transformer

import com.tn07.githubapp.domain.entities.GitUser
import com.tn07.githubapp.presentation.browser.uimodel.GitUserUiModel

/**
 * Created by toannguyen
 * Jun 26, 2021 at 11:03
 */
interface GitUserBrowserTransformer {
    fun transformGitUserUiModel(user: GitUser): GitUserUiModel
}
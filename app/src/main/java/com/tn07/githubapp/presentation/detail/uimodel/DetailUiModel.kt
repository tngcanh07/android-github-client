package com.tn07.githubapp.presentation.detail.uimodel

import androidx.annotation.StringRes

/**
 * Created by toannguyen
 * Jun 27, 2021 at 10:05
 */
sealed interface DetailState

data class UserDetailUiModel(
    val username: String,
    val name: String?,
    val avatar: String?,
    val publicRepos: String,
    val followers: String,
    val following: String,
    val blog: String?,
    val company: String?,
    val email: String?,
    val location: String?,
    val htmlUrl: String
) : DetailState

object LoadingUserDetail : DetailState

data class LoadDetailError(
    @StringRes val errorMessageStringRes: Int
) : DetailState
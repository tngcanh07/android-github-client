package com.tn07.githubapp.presentation.browser.uimodel

/**
 * Created by toannguyen
 * Jun 26, 2021 at 11:00
 */
sealed interface ListItem

data class GitUserUiModel(
    val avatar: String?,
    val username: String
) : ListItem
package com.tn07.githubapp.presentation.browser.uimodel

import androidx.annotation.StringRes

/**
 * Created by toannguyen
 * Jun 27, 2021 at 15:47
 */
sealed interface PageState {
    object Initializing : PageState
    object Success : PageState
    object Empty : PageState
    data class Error(@StringRes val errorMessageStringRes: Int) : PageState
    data class LoadingNextError(@StringRes val errorMessageStringRes: Int) : PageState
}
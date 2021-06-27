package com.tn07.githubapp.presentation.browser.uimodel

/**
 * Created by toannguyen
 * Jun 27, 2021 at 15:47
 */
sealed interface PageState {
    object Initializing : PageState
    object LoadingNext : PageState
    object Success : PageState
    object Error : PageState
}
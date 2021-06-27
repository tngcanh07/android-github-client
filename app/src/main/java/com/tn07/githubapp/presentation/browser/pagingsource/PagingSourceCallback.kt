package com.tn07.githubapp.presentation.browser.pagingsource

import com.tn07.githubapp.domain.entities.GitUser
import com.tn07.githubapp.domain.exceptions.DomainException

/**
 * Created by toannguyen
 * Jun 27, 2021 at 20:52
 */
interface PagingSourceCallback {
    fun onSuccess(page: Int, items: List<GitUser>)
    fun onError(page: Int, e: DomainException)
}
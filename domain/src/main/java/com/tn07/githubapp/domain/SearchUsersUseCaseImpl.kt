package com.tn07.githubapp.domain

import com.tn07.githubapp.domain.entities.GitUser
import com.tn07.githubapp.domain.entities.Pageable
import com.tn07.githubapp.domain.entities.SearchConfig
import javax.inject.Inject

/**
 * Created by toannguyen
 * Jun 25, 2021 at 21:17
 */
class SearchUsersUseCaseImpl @Inject constructor(
    private val repository: GitUserRepository
) : SearchUsersUseCase {

    override suspend fun searchFor(
        searchConfig: SearchConfig,
        page: Int,
        pageSize: Int
    ): Pageable<GitUser> {
        return repository.searchUsers(
            searchConfig = searchConfig,
            page = page,
            pageSize = pageSize
        )
    }
}
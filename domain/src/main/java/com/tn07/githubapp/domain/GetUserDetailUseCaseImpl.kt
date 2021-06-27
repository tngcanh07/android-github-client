package com.tn07.githubapp.domain

import com.tn07.githubapp.domain.entities.GitUserDetail
import javax.inject.Inject

/**
 * Created by toannguyen
 * Jun 25, 2021 at 21:40
 */
class GetUserDetailUseCaseImpl @Inject constructor(
    private val repository: GitUserRepository
) : GetUserDetailUseCase {

    override suspend fun getUserDetail(username: String): GitUserDetail {
        return repository.getUserDetail(username)
    }
}
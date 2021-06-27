package com.tn07.githubapp.domain

import com.tn07.githubapp.domain.entities.GitUserDetail

/**
 * Created by toannguyen
 * Jun 25, 2021 at 21:17
 */
interface GetUserDetailUseCase {
    suspend fun getUserDetail(username: String): GitUserDetail
}
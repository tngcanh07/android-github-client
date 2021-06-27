package com.tn07.githubapp.domain

import com.tn07.githubapp.domain.entities.GitUser
import com.tn07.githubapp.domain.entities.GitUserDetail
import com.tn07.githubapp.domain.entities.Pageable
import com.tn07.githubapp.domain.entities.SearchConfig

/**
 * Created by toannguyen
 * Jun 25, 2021 at 21:18
 */
interface GitUserRepository {
    suspend fun searchUsers(searchConfig: SearchConfig, page: Int, pageSize: Int): Pageable<GitUser>
    suspend fun getUserDetail(username: String): GitUserDetail
}
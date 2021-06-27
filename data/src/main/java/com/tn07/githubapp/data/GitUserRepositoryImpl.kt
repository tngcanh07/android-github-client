package com.tn07.githubapp.data

import com.tn07.githubapp.data.remote.GitUserRemoteDataSource
import com.tn07.githubapp.domain.GitUserRepository
import com.tn07.githubapp.domain.entities.GitUser
import com.tn07.githubapp.domain.entities.GitUserDetail
import com.tn07.githubapp.domain.entities.Pageable
import com.tn07.githubapp.domain.entities.SearchConfig
import javax.inject.Inject

/**
 * Created by toannguyen
 * Jun 25, 2021 at 21:18
 */
class GitUserRepositoryImpl @Inject constructor(
    private val remoteDataSource: GitUserRemoteDataSource
) : GitUserRepository {
    override suspend fun searchUsers(
        searchConfig: SearchConfig,
        page: Int,
        pageSize: Int
    ): Pageable<GitUser> {
        val response = remoteDataSource.searchUsers(
            searchConfig = searchConfig,
            page = page,
            pageSize = pageSize
        )

        return object : Pageable<GitUser> {
            override val totalCount: Int = response.totalCount
            override val page: Int = page
            override val pageSize: Int = pageSize
            override val items: List<GitUser> = response.items
        }
    }

    override suspend fun getUserDetail(username: String): GitUserDetail {
        return remoteDataSource.getUserDetail(username)
    }
}

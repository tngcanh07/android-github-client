package com.tn07.githubapp.data.remote

import com.tn07.githubapp.data.datamodel.GitUserDetailDataModel
import com.tn07.githubapp.data.datamodel.UserListResponse
import com.tn07.githubapp.domain.entities.SearchConfig

/**
 * Created by toannguyen
 * Jun 25, 2021 at 21:28
 */
interface GitUserRemoteDataSource {
    suspend fun searchUsers(
        searchConfig: SearchConfig,
        page: Int,
        pageSize: Int
    ): UserListResponse

    suspend fun getUserDetail(username: String): GitUserDetailDataModel
}
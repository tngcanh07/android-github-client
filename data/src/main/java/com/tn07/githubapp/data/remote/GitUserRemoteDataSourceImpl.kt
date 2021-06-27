package com.tn07.githubapp.data.remote

import com.tn07.githubapp.data.datamodel.GitUserDetailDataModel
import com.tn07.githubapp.data.datamodel.UserListResponse
import com.tn07.githubapp.data.remote.api.GithubApi
import com.tn07.githubapp.data.remote.api.request
import com.tn07.githubapp.domain.entities.SearchConfig
import javax.inject.Inject

/**
 * Created by toannguyen
 * Jun 25, 2021 at 21:29
 */
class GitUserRemoteDataSourceImpl @Inject constructor(
    private val api: GithubApi
) : GitUserRemoteDataSource {

    override suspend fun searchUsers(
        searchConfig: SearchConfig,
        page: Int,
        pageSize: Int
    ): UserListResponse {
        return request {
            api.searchUsers(
                buildSearchQuery(searchConfig),
                page = page,
                perPage = pageSize
            )
        }
    }

    internal fun buildSearchQuery(searchConfig: SearchConfig): String {
        return listOfNotNull(
            searchConfig.searchText,
            "type:${searchConfig.userType.typeId}"
        ).joinToString(separator = " ")
    }

    override suspend fun getUserDetail(username: String): GitUserDetailDataModel {
        return request {
            api.getUserDetail(username = username)
        }
    }
}
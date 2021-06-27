package com.tn07.githubapp.data.remote.api

import com.tn07.githubapp.data.datamodel.GitUserDetailDataModel
import com.tn07.githubapp.data.datamodel.UserListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by toannguyen
 * Jun 25, 2021 at 21:30
 */
interface GithubApi {
    @GET("search/users")
    suspend fun searchUsers(
        @Query(value = "q", encoded = true) query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<UserListResponse>

    @GET("users/{username}")
    suspend fun getUserDetail(
        @Path("username") username: String
    ): Response<GitUserDetailDataModel>
}
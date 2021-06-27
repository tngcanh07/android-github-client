package com.tn07.githubapp.data.datamodel

import com.google.gson.annotations.SerializedName


/**
 * Created by toannguyen
 * Jun 25, 2021 at 22:06
 */
data class UserListResponse(
    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("items")
    val items: List<GitUserDataModel>
)
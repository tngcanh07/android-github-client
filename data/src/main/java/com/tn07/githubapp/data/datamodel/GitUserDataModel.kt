package com.tn07.githubapp.data.datamodel

import com.google.gson.annotations.SerializedName
import com.tn07.githubapp.domain.entities.GitUser

/**
 * Created by toannguyen
 * Jun 25, 2021 at 21:34
 */
data class GitUserDataModel(
    @SerializedName("login")
    override val login: String,

    @SerializedName("id")
    override val id: Long,

    @SerializedName("avatar_url")
    override val avatarUrl: String?
) : GitUser
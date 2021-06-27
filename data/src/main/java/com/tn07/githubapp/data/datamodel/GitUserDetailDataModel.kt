package com.tn07.githubapp.data.datamodel

import com.google.gson.annotations.SerializedName
import com.tn07.githubapp.domain.entities.GitUserDetail

/**
 * Created by toannguyen
 * Jun 27, 2021 at 09:40
 */
data class GitUserDetailDataModel(
    @SerializedName("login")
    override val login: String,

    @SerializedName("id")
    override val id: Long,

    @SerializedName("avatar_url")
    override val avatarUrl: String?,

    @SerializedName("name")
    override val name: String?,

    @SerializedName("url")
    override val url: String,

    @SerializedName("html_url")
    override val htmlUrl: String,

    @SerializedName("public_repos")
    override val publicRepos: Int,

    @SerializedName("public_gists")
    override val publicGists: Int,

    @SerializedName("followers")
    override val followers: Int,

    @SerializedName("following")
    override val following: Int,

    @SerializedName("company")
    override val company: String?,

    @SerializedName("blog")
    override val blog: String?,

    @SerializedName("location")
    override val location: String?,

    @SerializedName("email")
    override val email: String?
) : GitUserDetail
package com.tn07.githubapp.domain.entities

/**
 * Created by toannguyen
 * Jun 25, 2021 at 21:16
 */
interface GitUserDetail : GitUser {
    val name: String?
    val url: String
    val publicRepos: Int
    val publicGists: Int
    val followers: Int
    val following: Int
    val company: String?
    val blog: String?
    val location: String?
    val email: String?
    val htmlUrl: String
}
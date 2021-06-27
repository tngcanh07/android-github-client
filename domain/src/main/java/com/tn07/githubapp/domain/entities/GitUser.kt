package com.tn07.githubapp.domain.entities

/**
 * Created by toannguyen
 * Jun 25, 2021 at 21:16
 */
interface GitUser {
    val id: Long
    val login: String
    val avatarUrl: String?
}
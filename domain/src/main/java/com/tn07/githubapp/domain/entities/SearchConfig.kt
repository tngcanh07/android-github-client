package com.tn07.githubapp.domain.entities

/**
 * Created by toannguyen
 * Jun 26, 2021 at 22:23
 */
interface SearchConfig {
    val searchText: String?
    val userType: UserType
}
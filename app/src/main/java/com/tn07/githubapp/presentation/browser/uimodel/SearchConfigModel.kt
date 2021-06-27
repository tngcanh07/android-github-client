package com.tn07.githubapp.presentation.browser.uimodel

import com.tn07.githubapp.domain.entities.SearchConfig
import com.tn07.githubapp.domain.entities.UserType

/**
 * Created by toannguyen
 * Jun 26, 2021 at 15:38
 */
data class SearchConfigModel(
    override val searchText: String? = null,
    override val userType: UserType = UserType.User
) : SearchConfig
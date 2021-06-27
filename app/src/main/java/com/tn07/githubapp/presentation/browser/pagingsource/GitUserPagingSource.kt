package com.tn07.githubapp.presentation.browser.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tn07.githubapp.domain.SearchUsersUseCase
import com.tn07.githubapp.domain.entities.GitUser
import com.tn07.githubapp.domain.exceptions.DomainException
import com.tn07.githubapp.presentation.browser.uimodel.SearchConfigModel
import javax.inject.Inject

/**
 * Created by toannguyen
 * Jun 26, 2021 at 13:40
 */
class GitUserPagingSource @Inject constructor(
    private val searchConfig: SearchConfigModel,
    private val searchUsersUseCase: SearchUsersUseCase,
    private val callback: PagingSourceCallback,
    private val startPageIndex: Int
) : PagingSource<Int, GitUser>() {

    override fun getRefreshKey(state: PagingState<Int, GitUser>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitUser> {
        val page = params.key ?: startPageIndex
        return try {
            val items = searchUsersUseCase.searchFor(
                searchConfig = searchConfig,
                page = page,
                pageSize = params.loadSize
            ).items
            callback.onSuccess(page, items)
            LoadResult.Page(
                data = items,
                prevKey = if (page <= startPageIndex) null else page - 1,
                nextKey = if (items.size < params.loadSize) null else page + 1
            )
        } catch (e: DomainException) {
            callback.onError(page, e)
            LoadResult.Error(e)
        }
    }
}
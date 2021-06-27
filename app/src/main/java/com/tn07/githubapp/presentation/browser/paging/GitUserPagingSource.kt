package com.tn07.githubapp.presentation.browser.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tn07.githubapp.domain.SearchUsersUseCase
import com.tn07.githubapp.domain.entities.GitUser
import javax.inject.Inject

/**
 * Created by toannguyen
 * Jun 26, 2021 at 13:40
 */
class GitUserPagingSource @Inject constructor(
    private val searchConfig: SearchConfigModel,
    private val searchUsersUseCase: SearchUsersUseCase,
    private val onStartLoading: (Int) -> Unit,
    private val onSuccess: (Int) -> Unit,
    private val onError: (Int) -> Unit,
    private val startPageIndex: Int
) : PagingSource<Int, GitUser>() {

    override fun getRefreshKey(state: PagingState<Int, GitUser>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitUser> {
        println(">>> load ${params.key}; $searchConfig")
        val page = params.key ?: startPageIndex
        return try {
            onStartLoading(page)
            val items = searchUsersUseCase.searchFor(
                searchConfig = searchConfig,
                page = page,
                pageSize = params.loadSize
            ).items
            onSuccess(page)
            LoadResult.Page(
                data = items,
                prevKey = if (page <= startPageIndex) null else page - 1,
                nextKey = if (items.size < params.loadSize) null else page + 1
            )
        } catch (e: Exception) {
            onError(page)
            LoadResult.Error(e)
        }
    }
}
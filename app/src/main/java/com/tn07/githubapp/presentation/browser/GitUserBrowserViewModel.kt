package com.tn07.githubapp.presentation.browser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.tn07.githubapp.domain.SearchUsersUseCase
import com.tn07.githubapp.presentation.browser.paging.GitUserPagingSource
import com.tn07.githubapp.presentation.browser.paging.SearchConfigModel
import com.tn07.githubapp.presentation.browser.transformer.GitUserBrowserTransformer
import com.tn07.githubapp.presentation.browser.uimodel.ListItem
import com.tn07.githubapp.presentation.browser.uimodel.PageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by toannguyen
 * Jun 26, 2021 at 10:27
 */
@HiltViewModel
class GitUserBrowserViewModel @Inject constructor(
    private val searchUsersUseCase: SearchUsersUseCase,
    private val transformer: GitUserBrowserTransformer
) : ViewModel() {
    private var searchFilter: SearchConfigModel = SearchConfigModel()

    private var setSearchTextJob: Job? = null

    private var currentPagingSource: GitUserPagingSource? = null

    private val pagingConfig: PagingConfig = PagingConfig(
        pageSize = 50,
        maxSize = 1000,
        initialLoadSize = 50,
        prefetchDistance = 20,
        enablePlaceholders = false
    )

    val gitUserListResult: Flow<PagingData<ListItem>> = Pager(pagingConfig) {
        GitUserPagingSource(
            searchFilter,
            searchUsersUseCase,
            onStartLoading = this::onStartLoading,
            onSuccess = this::onSuccess,
            onError = this::onError,
            startPageIndex = START_PAGE_INDEX
        ).also {
            currentPagingSource = it
        }
    }
        .flow
        .map {
            it.map(transformer::transformGitUserUiModel)
        }

    private val _pageStateLiveData = MutableLiveData<PageState>(PageState.Initializing)
    val pageStateLiveData: LiveData<PageState> get() = _pageStateLiveData


    private fun onStartLoading(page: Int) {
        if (page > START_PAGE_INDEX) {
            _pageStateLiveData.postValue(PageState.LoadingNext)
        }
    }

    private fun onSuccess(page: Int) {
        _pageStateLiveData.postValue(PageState.Success)
    }

    private fun onError(page: Int) {
        _pageStateLiveData.postValue(PageState.Error)
    }

    @Synchronized
    fun setSearchText(searchText: String?) {
        if (searchText.orEmpty() != searchFilter.searchText.orEmpty()) {
            // cancel previous task
            if (setSearchTextJob?.isActive == true) {
                setSearchTextJob?.cancel()
            }
            setSearchTextJob = viewModelScope.launch {
                delay(300)
                searchFilter = searchFilter.copy(searchText = searchText)
                currentPagingSource?.invalidate()
            }
        }
    }

    companion object {
        const val START_PAGE_INDEX = 1
    }
}
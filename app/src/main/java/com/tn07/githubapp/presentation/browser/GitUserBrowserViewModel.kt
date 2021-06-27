package com.tn07.githubapp.presentation.browser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.tn07.githubapp.di.IoDispatcher
import com.tn07.githubapp.domain.SearchUsersUseCase
import com.tn07.githubapp.domain.entities.GitUser
import com.tn07.githubapp.domain.exceptions.DomainException
import com.tn07.githubapp.presentation.browser.pagingsource.GitUserPagingSource
import com.tn07.githubapp.presentation.browser.pagingsource.PagingSourceCallback
import com.tn07.githubapp.presentation.browser.transformer.GitUserBrowserTransformer
import com.tn07.githubapp.presentation.browser.uimodel.ListItem
import com.tn07.githubapp.presentation.browser.uimodel.PageState
import com.tn07.githubapp.presentation.browser.uimodel.SearchConfigModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
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
    private val transformer: GitUserBrowserTransformer,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private var searchFilter: SearchConfigModel = SearchConfigModel()

    private var setSearchTextJob: Job? = null

    private var currentPagingSource: GitUserPagingSource? = null

    private val pagingConfig: PagingConfig = PagingConfig(
        pageSize = PAGE_SIZE,
        maxSize = MAX_CAPACITY,
        initialLoadSize = PAGE_SIZE,
        prefetchDistance = PREFETCH_DISTANCE
    )

    private val pagingSourceCallback = object : PagingSourceCallback {
        override fun onSuccess(page: Int, items: List<GitUser>) {
            if (page == START_PAGE_INDEX && items.isEmpty()) {
                _pageStateLiveData.postValue(PageState.Empty)
            } else {
                _pageStateLiveData.postValue(PageState.Success)
            }
        }

        override fun onError(page: Int, e: DomainException) {
            if (page == START_PAGE_INDEX) {
                _pageStateLiveData.postValue(transformer.transformErrorState(e))
            } else {
                _pageStateLiveData.postValue(transformer.transformLoadingNextError(e))
            }
        }
    }

    val gitUserListResult: Flow<PagingData<ListItem>> = Pager(pagingConfig) {
        GitUserPagingSource(
            searchFilter,
            searchUsersUseCase,
            callback = pagingSourceCallback,
            startPageIndex = START_PAGE_INDEX
        ).also {
            currentPagingSource = it
        }
    }
        .flow
        .map {
            it.map(transformer::transformGitUserUiModel)
        }

    private val _pageStateLiveData = MutableLiveData<PageState>()
    val pageStateLiveData: LiveData<PageState> get() = _pageStateLiveData


    @Synchronized
    fun setSearchText(searchText: String?) {
        if (searchText.orEmpty() != searchFilter.searchText.orEmpty()) {
            _pageStateLiveData.postValue(PageState.Initializing)
            // cancel previous task
            if (setSearchTextJob?.isActive == true) {
                setSearchTextJob?.cancel()
            }
            setSearchTextJob = viewModelScope.launch(ioDispatcher) {
                delay(SET_TEXT_DELAY_DURATION)
                searchFilter = searchFilter.copy(searchText = searchText)
                currentPagingSource?.invalidate()
            }
        }
    }

    fun refresh() {
        _pageStateLiveData.postValue(PageState.Initializing)
        // cancel previous task
        if (setSearchTextJob?.isActive == true) {
            setSearchTextJob?.cancel()
        }
        setSearchTextJob = viewModelScope.launch(ioDispatcher) {
            delay(REFRESH_DELAY_DURATION)
            currentPagingSource?.invalidate()
        }
    }

    companion object {
        const val START_PAGE_INDEX = 1
        const val PAGE_SIZE = 100
        const val PREFETCH_DISTANCE = 10
        const val MAX_CAPACITY = 1000

        const val SET_TEXT_DELAY_DURATION = 300L
        const val REFRESH_DELAY_DURATION = 100L
    }
}
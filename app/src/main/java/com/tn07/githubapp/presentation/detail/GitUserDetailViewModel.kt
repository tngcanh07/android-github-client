package com.tn07.githubapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tn07.githubapp.domain.GetUserDetailUseCase
import com.tn07.githubapp.domain.exceptions.DomainException
import com.tn07.githubapp.presentation.detail.transformer.GitUserDetailTransformer
import com.tn07.githubapp.presentation.detail.uimodel.DetailState
import com.tn07.githubapp.presentation.detail.uimodel.LoadingUserDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by toannguyen
 * Jun 27, 2021 at 09:29
 */
@HiltViewModel
class GitUserDetailViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val transformer: GitUserDetailTransformer
) : ViewModel() {

    private val _userDetailLiveData = MutableLiveData<DetailState>()

    val userDetailLiveData: LiveData<DetailState> get() = _userDetailLiveData

    private var loadingJob: Job? = null

    @Synchronized
    fun loadUserDetail(username: String) {
        if (loadingJob?.isActive == true) {
            loadingJob?.cancel()
        }
        loadingJob = viewModelScope.launch {
            _userDetailLiveData.postValue(LoadingUserDetail)
            try {
                getUserDetailUseCase.getUserDetail(username)
                    .let(transformer::transformUserDetail)
                    .let(_userDetailLiveData::postValue)
            } catch (e: DomainException) {
                transformer.transformErrorState(e)
                    .let(_userDetailLiveData::postValue)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        loadingJob = null
    }
}
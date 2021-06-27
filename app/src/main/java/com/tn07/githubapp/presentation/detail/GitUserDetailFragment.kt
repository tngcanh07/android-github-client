package com.tn07.githubapp.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.tn07.githubapp.common.fadeInAnimation
import com.tn07.githubapp.databinding.DetailFragmentBinding
import com.tn07.githubapp.presentation.detail.uimodel.DetailState
import com.tn07.githubapp.presentation.detail.uimodel.LoadDetailError
import com.tn07.githubapp.presentation.detail.uimodel.LoadingUserDetail
import com.tn07.githubapp.presentation.detail.uimodel.UserDetailUiModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by toannguyen
 * Jun 27, 2021 at 09:29
 */
@AndroidEntryPoint
class GitUserDetailFragment : Fragment() {
    private val viewModel: GitUserDetailViewModel by viewModels()

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val fragmentArgs by navArgs<GitUserDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchDetail()
        binding.swipeRefreshLayout.setOnRefreshListener {
            fetchDetail()
        }
        binding.stateError.retryButton.setOnClickListener {
            fetchDetail()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.userDetailLiveData.observe(viewLifecycleOwner) {
            it?.let(this::bind)
        }
    }

    private fun fetchDetail() {
        viewModel.loadUserDetail(fragmentArgs.username)
    }

    private fun bind(uiModel: DetailState) {
        when (uiModel) {
            is UserDetailUiModel -> showUserDetail(uiModel)
            is LoadingUserDetail -> showLoading()
            is LoadDetailError -> showError(uiModel)
        }
    }

    private fun showLoading() {
        binding.swipeRefreshLayout.isRefreshing = true
        binding.stateSuccess.root.visibility = View.GONE
        binding.stateError.root.visibility = View.GONE
    }

    private fun showError(uiModel: LoadDetailError) {
        binding.swipeRefreshLayout.isRefreshing = false
        binding.stateSuccess.root.visibility = View.GONE
        binding.stateError.let { binding ->
            binding.root.visibility = View.VISIBLE
            binding.errorMessage.setText(uiModel.errorMessageStringRes)
        }
    }

    private fun showUserDetail(uiModel: UserDetailUiModel) {
        binding.swipeRefreshLayout.isRefreshing = false
        binding.stateError.root.visibility = View.GONE
        binding.stateSuccess.let { binding ->
            binding.root.fadeInAnimation()
            binding.name.text = uiModel.name
            binding.userName.text = uiModel.username
        }
    }
}
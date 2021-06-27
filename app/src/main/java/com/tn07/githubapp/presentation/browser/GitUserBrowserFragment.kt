package com.tn07.githubapp.presentation.browser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tn07.githubapp.databinding.BrowserFragmentBinding
import com.tn07.githubapp.presentation.browser.uimodel.GitUserUiModel
import com.tn07.githubapp.presentation.browser.uimodel.PageState
import com.tn07.githubapp.presentation.browser.views.GitUserAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

/**
 * Created by toannguyen
 * Jun 26, 2021 at 10:26
 */
@AndroidEntryPoint
class GitUserBrowserFragment : Fragment() {
    private val viewModel: GitUserBrowserViewModel by viewModels()
    private val navigator: GitUserBrowserNavigator
        get() = requireActivity() as GitUserBrowserNavigator

    // This property is only valid between onCreateView and
    // onDestroyView.
    private var _binding: BrowserFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BrowserFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)
        binding.borrowStateError.retryButton.setOnClickListener {
            viewModel.refresh()
        }
        binding.searchTextInput.addTextChangedListener {
            viewModel.setSearchText(it?.toString())
        }

        lifecycleScope.launchWhenCreated {
            viewModel.gitUserListResult.collectLatest { pagingData ->
                GitUserAdapter(::onGitUserClicked).also {
                    it.submitData(lifecycle, pagingData)
                    binding.recyclerView.adapter = it
                }
            }
        }
        viewModel.pageStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                PageState.Initializing -> showLoadingPage()
                PageState.Success -> showSuccessPage()
                PageState.Empty -> showEmptyPage()
                is PageState.Error -> showErrorPage(it)
                is PageState.LoadingNextError -> showLoadingNextError(it)
            }
        }
    }

    private fun showLoadingPage() {
        binding.borrowStateLoading.root.visibility = View.VISIBLE
        binding.borrowStateError.root.visibility = View.GONE
        binding.borrowStateEmpty.root.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
        binding.recyclerView.adapter = null
    }

    private fun showSuccessPage() {
        binding.recyclerView.visibility = View.VISIBLE
        binding.borrowStateLoading.root.visibility = View.GONE
        binding.borrowStateError.root.visibility = View.GONE
        binding.borrowStateEmpty.root.visibility = View.GONE
    }

    private fun showEmptyPage() {
        binding.borrowStateEmpty.root.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        binding.borrowStateLoading.root.visibility = View.GONE
        binding.borrowStateError.root.visibility = View.GONE
    }

    private fun showErrorPage(state: PageState.Error) {
        binding.recyclerView.visibility = View.GONE
        binding.borrowStateEmpty.root.visibility = View.GONE
        binding.borrowStateLoading.root.visibility = View.GONE
        binding.borrowStateError.let {
            it.root.visibility = View.VISIBLE
            it.errorMessage.setText(state.errorMessageStringRes)
        }
    }

    private fun showLoadingNextError(state: PageState.LoadingNextError) {
        Toast.makeText(requireActivity(), state.errorMessageStringRes, Toast.LENGTH_SHORT)
            .show()
    }

    private fun onGitUserClicked(gitUser: GitUserUiModel) {
        navigator.onOpenUserDetail(gitUser.username)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

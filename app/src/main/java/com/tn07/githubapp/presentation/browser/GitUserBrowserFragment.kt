package com.tn07.githubapp.presentation.browser

import android.content.Context
import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import com.tn07.githubapp.R
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

    private var _binding: BrowserFragmentBinding? = null
    private var _adapter: GitUserAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = requireNotNull(_binding)
    private val adapter get() = requireNotNull(_adapter)

    private var searchTextWatcher: TextWatcher? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BrowserFragmentBinding.inflate(inflater, container, false)
        _adapter = GitUserAdapter(this::onGitUserClicked)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)

        lifecycleScope.launchWhenCreated {
            viewModel.gitUserListResult.collectLatest {
                adapter.submitData(lifecycle, it)
                println(">>> gitUserListResult $adapter")
            }
        }
        viewModel.pageStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                PageState.Initializing -> showLoadingPage()
                PageState.Success -> showSuccessPage()
                PageState.LoadingNext -> showLoadingIndicator()
                PageState.Error -> showErrorPage()
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
        menu.findItem(R.id.action_search)?.let(::initSearchView)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun showLoadingPage() {
        binding.recyclerView.visibility = View.GONE
    }

    private fun showSuccessPage() {
        binding.recyclerView.visibility = View.VISIBLE
    }

    private fun showLoadingIndicator() {

    }

    private fun showErrorPage() {

    }

    private fun initSearchView(menuItem: MenuItem) {
        val searchEditText =
            menuItem.actionView.findViewById<TextInputEditText>(R.id.search_text_input)

        menuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                searchEditText.postDelayed({
                    searchEditText.requestFocus()
                    (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)
                        ?.showSoftInput(searchEditText, 0)
                    searchTextWatcher = searchEditText.addTextChangedListener { editable ->
                        editable?.toString()?.let {
                            viewModel.setSearchText(it)
                        }
                    }
                }, 300)
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                searchEditText.text = null
                searchEditText.clearFocus()
                searchTextWatcher?.let(searchEditText::removeTextChangedListener)
                return true
            }
        })
    }

    private fun onGitUserClicked(gitUser: GitUserUiModel) {
        navigator.onOpenUserDetail(gitUser.username)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _adapter = null
    }
}

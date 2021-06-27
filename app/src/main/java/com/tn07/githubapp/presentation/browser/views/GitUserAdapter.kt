package com.tn07.githubapp.presentation.browser.views

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tn07.githubapp.presentation.browser.uimodel.GitUserUiModel
import com.tn07.githubapp.presentation.browser.uimodel.ListItem

/**
 * Created by toannguyen
 * Jun 26, 2021 at 10:58
 */
class GitUserAdapter(
    private val onGitUserClicked: (GitUserUiModel) -> Unit
) : PagingDataAdapter<ListItem, RecyclerView.ViewHolder>(
    GitUserDiffUtilCallback()
) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is GitUserViewHolder) {
            holder.bind(getItem(position) as GitUserUiModel)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GitUserViewHolder(parent, onGitUserClicked)
    }
}
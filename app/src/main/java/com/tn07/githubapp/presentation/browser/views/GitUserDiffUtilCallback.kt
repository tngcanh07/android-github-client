package com.tn07.githubapp.presentation.browser.views

import androidx.recyclerview.widget.DiffUtil
import com.tn07.githubapp.presentation.browser.uimodel.ListItem

/**
 * Created by toannguyen
 * Jun 26, 2021 at 11:13
 */
class GitUserDiffUtilCallback : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem == newItem
    }
}
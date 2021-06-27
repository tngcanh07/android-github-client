package com.tn07.githubapp.presentation.browser.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tn07.githubapp.R
import com.tn07.githubapp.databinding.BrowserUserItemBinding
import com.tn07.githubapp.presentation.browser.uimodel.GitUserUiModel

/**
 * Created by toannguyen
 * Jun 26, 2021 at 11:17
 */
class GitUserViewHolder(
    parent: ViewGroup,
    onItemClicked: (GitUserUiModel) -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.browser_user_item, parent, false)
) {
    private val binding = BrowserUserItemBinding.bind(itemView)
    private var gitUser: GitUserUiModel? = null

    init {
        itemView.setOnClickListener {
            gitUser?.let(onItemClicked)
        }
    }

    fun bind(gitUser: GitUserUiModel) {
        this.gitUser = gitUser
        binding.userName.text = gitUser.username


        Glide.with(itemView)
            .load(gitUser.avatar)
            .error(R.drawable.ic_baseline_person_24)
            .circleCrop()
            .into(binding.avatar)
    }
}
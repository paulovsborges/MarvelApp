package com.pvsb.marvelapp.presentation.home

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class LoadingStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<LoadMoreStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadMoreStateViewHolder {
        return LoadMoreStateViewHolder.create(parent, retry)
    }

    override fun onBindViewHolder(holder: LoadMoreStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}
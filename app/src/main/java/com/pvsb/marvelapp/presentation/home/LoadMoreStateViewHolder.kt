package com.pvsb.marvelapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.pvsb.marvelapp.databinding.LoadMoreStateBinding

class LoadMoreStateViewHolder(
    private val binding: LoadMoreStateBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private val inflateBinding = LoadMoreStateBinding.bind(itemView)
    private val progress = inflateBinding.progressLoadMore
    private val tryAgain = inflateBinding.textTryAgain.also {
        it.setOnClickListener {
            retry()
        }
    }

    fun bind(loadState: LoadState) {
        progress.isVisible = loadState is LoadState.Loading
        tryAgain.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): LoadMoreStateViewHolder {
            return LoadMoreStateViewHolder(
                LoadMoreStateBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                ),
                retry
            )
        }
    }
}
package com.pvsb.marvelapp.presentation.home

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.pvsb.core.domain.model.Character
import com.pvsb.marvelapp.utils.BaseDiffUtil

class MainAdapter : PagingDataAdapter<Character, CharactersViewHolder>(BaseDiffUtil<Character>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}
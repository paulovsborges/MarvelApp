package com.pvsb.marvelapp.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.pvsb.core.domain.model.Characters
import com.pvsb.marvelapp.utils.BaseDiffUtil

class MainAdapter : ListAdapter<Characters, CharactersViewHolder>(BaseDiffUtil<Characters>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
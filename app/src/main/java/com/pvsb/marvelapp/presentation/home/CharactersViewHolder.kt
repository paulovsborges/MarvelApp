package com.pvsb.marvelapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pvsb.core.domain.model.Character
import com.pvsb.marvelapp.R
import com.pvsb.marvelapp.databinding.CharactersCardMainBinding

class CharactersViewHolder(private val binding: CharactersCardMainBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val name = binding.characterName
    private val image = binding.characterImage

    fun bind(item: Character) {

        name.text = item.name
        Glide.with(binding.root)
            .load(item.image)
            .placeholder(R.drawable.ic_avengers)
            .centerCrop()
            .into(image)
    }

    companion object {

        fun inflate(parent: ViewGroup): CharactersViewHolder {
            val binding = CharactersCardMainBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return CharactersViewHolder(binding)
        }
    }
}


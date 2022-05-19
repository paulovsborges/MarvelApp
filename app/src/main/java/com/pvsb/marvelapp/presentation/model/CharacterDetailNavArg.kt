package com.pvsb.marvelapp.presentation.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class CharacterDetailNavArg(
    val id: Int,
    val name: String,
    val imageUrl: String,
) : Parcelable

package com.example.mediafeed

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

enum class MediaType { IMAGE, VIDEO }

data class ImagePost(
    val id: String,
    val name: String,
    @DrawableRes val drawableRes: Int
)

data class VideoPost(
    val id: String,
    val name: String,
    @RawRes val rawRes: Int
)
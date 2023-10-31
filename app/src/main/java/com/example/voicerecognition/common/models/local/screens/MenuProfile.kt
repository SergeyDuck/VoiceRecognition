package com.example.voicerecognition.common.models.local.screens

import com.example.voicerecognition.common.models.res.TextApp

enum class MenuProfile {
    MEDIA,
    WISHLIST,
    AFFAIRS,
    AWARDS;

    fun getTextMenu() = when (this) {
        MEDIA -> TextApp.media
        WISHLIST -> TextApp.wishList
        AFFAIRS -> TextApp.affairs
        AWARDS -> TextApp.awards
    }
}

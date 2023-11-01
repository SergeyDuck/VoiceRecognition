package ru.axas.spechrecognizer.common.models.local.screens

import ru.axas.spechrecognizer.common.models.res.TextApp

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

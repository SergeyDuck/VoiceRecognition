package com.example.voicerecognition.common.models.local.screens

import com.example.voicerecognition.common.models.res.TextApp

enum class MenuMediaRibbon {
    MY,
    ALL,
    FAMILY,
    FAVORITES;

    fun getTextMenu() = when (this) {
        MY  -> TextApp.myMedia
        ALL -> TextApp.all
        FAMILY   -> TextApp.family
        FAVORITES -> TextApp.favorites
    }
}

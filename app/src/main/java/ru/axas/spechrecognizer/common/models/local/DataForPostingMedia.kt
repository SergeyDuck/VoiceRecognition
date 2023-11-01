package ru.axas.spechrecognizer.common.models.local


data class DataForPostingMedia(
    val uri: String,
    val name: String? = null,
    val description: String? = null,
    val happened: Long? = null,
    val isFavorite: Boolean? = null,
    val albumId: Int? = null,
    val address: String? = null,
    val lat: Int? = null,
    val lon: Int? = null
)

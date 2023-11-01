package ru.axas.spechrecognizer.common.models.local

import kotlinx.serialization.Serializable

@Serializable
data class LocalFileValue(
    val domen:String? = null,
    val url:String? = null,
)



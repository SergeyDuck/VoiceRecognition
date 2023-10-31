package com.example.voicerecognition.common.models.local

import kotlinx.serialization.Serializable

@Serializable
data class LocalFileValue(
    val chooserFamilyId: Int? = null,
    val token:String? = null,
    val tokenFirebase:String? = null,
)



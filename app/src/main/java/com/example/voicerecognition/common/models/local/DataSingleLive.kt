package com.example.voicerecognition.common.models.local


data class DataSingleLive(
    val isLoad: Boolean = false,
    val messageSnack: EventProject<String?> = EventProject(null),
)




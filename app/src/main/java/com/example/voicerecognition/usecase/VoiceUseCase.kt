package com.example.voicerecognition.usecase

import com.example.voicerecognition.network.api.ApiAlbums
import com.example.voicerecognition.network.model.SendingData

class VoiceUseCase(
    private val api: ApiAlbums
) {
    suspend fun postText(
        text: String,
        flowStart: () -> Unit = {},
        flowSuccess: (String?) -> Unit,
        flowStop: () -> Unit = {},
    ) {
        flowStart.invoke()

        val response = api.postAlbums(body = SendingData(message = text))
        flowSuccess.invoke(response?.message)
        flowStop.invoke()
    }

}
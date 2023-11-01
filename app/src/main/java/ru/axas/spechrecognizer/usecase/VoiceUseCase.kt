package ru.axas.spechrecognizer.usecase

import ru.axas.spechrecognizer.network.api.ApiAlbums
import ru.axas.spechrecognizer.network.model.SendingData

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
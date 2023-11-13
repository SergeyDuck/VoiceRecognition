package ru.axas.spechrecognizer.usecase

import ru.axas.spechrecognizer.network.api.ApiAlbums
import ru.axas.spechrecognizer.network.model.Contact

class VoiceUseCase(
    private val api: ApiAlbums
) {
    suspend fun postData(
        listContact:List<Contact>,
        flowStart: () -> Unit = {},
        flowSuccess: (String?) -> Unit,
        flowStop: () -> Unit = {},
        flowError: () -> Unit = {},
    ) {
        flowStart.invoke()

        val response = api.postData(
            body = listContact
        )
        if (response == null) {
            flowError.invoke()
        }
        else {
            flowSuccess.invoke(response.message)
        }
        flowStop.invoke()
    }

    suspend fun getData(
        flowStart: () -> Unit = {},
        flowSuccess: (List<Contact>) -> Unit,
        flowStop: () -> Unit = {},
    ) {
        flowStart.invoke()

        val response = api.getData()
        flowSuccess.invoke(response)
        flowStop.invoke()
    }

}
package ru.axas.spechrecognizer.network.api

import ru.axas.spechrecognizer.common.models.logger.LogCustom.logD
import ru.axas.spechrecognizer.network.Client
import ru.axas.spechrecognizer.network.model.SendingData
import ru.axas.spechrecognizer.network.util.postWithBody
import ru.axas.spechrecognizer.store.FileStoreApp
import io.ktor.client.call.body

class ApiAlbums(
    private val client: Client,
    private val dataStore: FileStoreApp,
) {
    /**
     * postAlbums
     */
    suspend fun postAlbums(
        body: SendingData,
    ): SendingData? {
        return try {
            val strUrl =
                "http://" + dataStore.getLocalData().address + ":" + dataStore.getLocalData().port + "/recognition/sendMessage"
            logD("strUrl: $strUrl")
            val response = client.api.postWithBody(
                urlString = strUrl,
                body = body
            )
            response.body<SendingData>()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


}
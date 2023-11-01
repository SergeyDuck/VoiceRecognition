package com.example.voicerecognition.network.api

import com.example.voicerecognition.common.models.logger.LogCustom.logD
import com.example.voicerecognition.network.Client
import com.example.voicerecognition.network.model.SendingData
import com.example.voicerecognition.network.util.postWithBody
import com.example.voicerecognition.store.FileStoreApp
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
            val strUrl = "http://"+dataStore.getLocalData().domen +":"+ dataStore.getLocalData().url+"/recognition/sendMessage"
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
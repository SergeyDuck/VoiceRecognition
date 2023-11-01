package com.example.voicerecognition.network.api

import com.example.voicerecognition.network.Client
import com.example.voicerecognition.network.model.SendingData
import com.example.voicerecognition.network.util.postWithBody
import io.ktor.client.call.body

class ApiAlbums(
    private val client: Client
) {
    /**
     * postAlbums
     */
    suspend fun postAlbums(
        body: SendingData,
    ): SendingData? {
        return try {
            val response = client.api.postWithBody(
                urlString = "/recognition/sendMessage",
                body = body
            )
            response.body<SendingData>()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


}
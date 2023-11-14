package ru.axas.contacts.network.api

import ru.axas.contacts.common.models.logger.LogCustom.logD
import ru.axas.contacts.network.Client
import ru.axas.contacts.network.model.SendingData
import ru.axas.contacts.network.util.postWithBody
import ru.axas.contacts.store.FileStoreApp
import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.axas.contacts.network.model.BaseResponse
import ru.axas.contacts.network.model.Contact

class ApiAlbums(
    private val client: Client,
    private val dataStore: FileStoreApp,
) {
    /**
     * POST
     *
     * Import Contacts
     */
    suspend fun postData(
        body: List<Contact>,
    ): BaseResponse? {
        return try {
            val strUrl =
                "/mobile-prs/contacts/import"
            logD("strUrl: $strUrl")
            val response = client.api.postWithBody(
                urlString = strUrl,
                body = body
            )
            response.body<BaseResponse>()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * GET
     *
     * Export Contacts
     * */
    suspend fun getData(): List<Contact> {
        return try {
            val strUrl =
                "/mobile-prs/contacts/export"
            logD("strUrl: $strUrl")
            val response = client.api.get(strUrl)
            response.body<List<Contact>>()
        } catch (e: Exception) {
            e.printStackTrace()
            listOf()
        }
    }


}
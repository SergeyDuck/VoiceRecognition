package ru.axas.contacts.network.util

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser.parseString
import com.google.gson.JsonSyntaxException
import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.Headers
import io.ktor.http.HttpProtocolVersion
import io.ktor.http.HttpStatusCode
import io.ktor.util.InternalAPI
import io.ktor.util.date.GMTDate
import io.ktor.utils.io.ByteReadChannel
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


suspend inline fun <reified T> HttpClient.postWithBody(
    urlString: String, body: T
) = post(urlString) { setBody(body) }

suspend inline fun <reified T> HttpClient.getWithBody(
    urlString: String, body: T
) = get(urlString) { setBody(body) }

suspend inline fun <reified T> HttpClient.putWithBody(
    urlString: String, body: T
) = put(urlString) { setBody(body) }

suspend inline fun <reified T> HttpClient.deleteWithBody(
    urlString: String, body: T
) = delete(urlString) { setBody(body) }

internal val loggerPretty = object : Logger {
    private val BODY_START = "BODY START"
    private val BODY_END = "BODY END"
    private val LOG_NAME = "HTTP Client"
    override fun log(message: String) {
        val startBody = message.indexOf(BODY_START)
        val endBody = message.indexOf(BODY_END)
        if (startBody != -1 && endBody != -1) {
            try {
                val header = message.substring(0, startBody)
                val jsonBody = message.substring(startBody + BODY_START.length, endBody)
                val prettyPrintJson = GsonBuilder().setPrettyPrinting()
                    .create().toJson(parseString(jsonBody))
                println(LOG_NAME + "$header\n$prettyPrintJson")
            } catch (m: JsonSyntaxException) {
                println(LOG_NAME + message)
            }
        } else {
            println(LOG_NAME + message)

        }
    }
}

fun customResp(body: String, code: Int) = object : HttpResponse() {
    override val call: HttpClientCall
        get() = HttpClientCall(HttpClient())

    @InternalAPI
    override val content: ByteReadChannel
        get() = ByteReadChannel(body)
    override val coroutineContext: CoroutineContext
        get() = EmptyCoroutineContext
    override val headers: Headers
        get() = Headers.Empty
    override val requestTime: GMTDate
        get() = GMTDate.START
    override val responseTime: GMTDate
        get() = GMTDate.START
    override val status: HttpStatusCode
        get() = HttpStatusCode.fromValue(code)
    override val version: HttpProtocolVersion
        get() = HttpProtocolVersion.HTTP_2_0

}

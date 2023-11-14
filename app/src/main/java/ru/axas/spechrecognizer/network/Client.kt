package ru.axas.contacts.network

import ru.axas.contacts.common.models.res.TextApp.isDebug
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.head
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.takeFrom
import io.ktor.serialization.gson.gson
import io.ktor.utils.io.core.Closeable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.axas.contacts.network.util.loggerPretty

class Client() :
    Closeable {

    companion object {
        const val BASE_URL: String = "http://171-25.dev.apidrom.ru:10510"

        suspend fun isInternetAccessible(): Boolean {
            return withContext(Dispatchers.IO) {
                try {
                    HttpClient {}.head(BASE_URL)
                    true
                } catch (e: Exception) {
                    e.printStackTrace()
                    false
                }
            }
        }
    }

    val api = HttpClient {
        defaultRequestOf()
        httpTimeoutOf()
        contentNegotiationOf()
        loggingOf()
        httpResponseValidatorOf()
    }


    private fun HttpClientConfig<*>.defaultRequestOf() = defaultRequest {
        url.takeFrom(BASE_URL)
        headers {
            contentType(ContentType.Application.Json)
            header("Content-Type", "application/x-www-form-urlencoded")
        }
    }

    private fun HttpClientConfig<*>.contentNegotiationOf() = install(ContentNegotiation) {
        gson {
            setLenient()
        }
    }

    private fun HttpClientConfig<*>.loggingOf() = if (isDebug) {
        install(Logging) {
            logger = loggerPretty
            level = LogLevel.ALL
        }
    }
    else Unit

    private fun HttpClientConfig<*>.httpTimeoutOf() = install(HttpTimeout) {
        socketTimeoutMillis = 520_000
        requestTimeoutMillis = 360_000
        connectTimeoutMillis = 360_000
    }

    private fun HttpClientConfig<*>.httpResponseValidatorOf() = HttpResponseValidator {
        validateResponse {
            println("APIClient, HttpResponseValidator: ${it.status}")

        }
    }

    override fun close() {
        api.close()
    }
}

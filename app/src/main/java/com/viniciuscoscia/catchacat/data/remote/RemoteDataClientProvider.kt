package com.viniciuscoscia.catchacat.data.remote

import com.viniciuscoscia.catchacat.BuildConfig
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import timber.log.Timber

private const val DEFAULT_TIMEOUT_MILLISECONDS = 5_000L
private const val API_KEY_HEADER = "x-api-key"

fun provideKtorHttpClient() = HttpClient(Android) {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }

    install(HttpTimeout) {
        requestTimeoutMillis = DEFAULT_TIMEOUT_MILLISECONDS
        connectTimeoutMillis = DEFAULT_TIMEOUT_MILLISECONDS
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Timber.tag("Logger Ktor").v(message)
            }
        }
        level = LogLevel.ALL
    }

    install(ResponseObserver) {
        onResponse { response ->
            Timber.tag("HTTP status").d(response.status.value.toString())
        }
    }

    defaultRequest {
        url(BuildConfig.BaseUrl)
        header(HttpHeaders.ContentType, ContentType.Application.Json)
        header(API_KEY_HEADER, BuildConfig.API_KEY)
    }
}